package com.example.runas.Perfil

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.runas.DBControler.Usuario
import com.example.runas.DBControler.UsuarioDatabase
import com.example.runas.Login.Login
import com.example.runas.R
import com.example.runas.Runas.MenuRunas
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

class Perfil : AppCompatActivity() {

    var elUsuario: Usuario? = null

    var imageUri: Uri? = null
    var usuario: String = ""
    var imagenRic: Bitmap? = null

    var entrada = false
    var id_usuario: Long = -1
    private val PICK_IMAGE_REQUEST = 1
    private val READ_EXTERNAL_STORAGE_PERMISSION_REQUEST = 100

    lateinit var database: UsuarioDatabase
    lateinit var uri: Uri
    lateinit var imageViewUsuario: ImageView
    lateinit var editTextUser: EditText
    lateinit var editTextPass: EditText
    lateinit var editTextPassAgain: EditText
    lateinit var btnCamara: Button
    lateinit var btnGaleria: Button
    lateinit var btnSave: Button
    lateinit var btnCancel: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)
        database = UsuarioDatabase(this)
        val inetntBtnLogin = Intent(this, Login::class.java)

        id_usuario = intent.getLongExtra("id_usuario", 500)
        if (id_usuario != 0L && id_usuario != 500L) {
            GlobalScope.launch(Dispatchers.Main) {
                elUsuario = database.usuarioDao().getUserById(id_usuario)
                if (elUsuario != null) {

                    editTextUser.setText(elUsuario?.usuario ?: "")
                    if(elUsuario.imagen != []){

                    }
                    imagenRic = byteArrayToBitmap(elUsuario.imagen)
                    imageViewUsuario.setImageBitmap(imagenRic)
                } else {

                    startActivity(inetntBtnLogin)
                    showToast("Error al coger el usuario")
                }
            }
        } else {
            startActivity(inetntBtnLogin)
        }

        /**
         * Inicializamos
         */
        editTextUser = findViewById(R.id.editTextUser)
        editTextPass = findViewById(R.id.editTextPass)
        editTextPassAgain = findViewById(R.id.editTextPassAgain)
        btnCamara = findViewById(R.id.btnCamara)
        btnGaleria = findViewById(R.id.btnGaleria)
        btnSave = findViewById(R.id.btnSave)
        btnCancel = findViewById(R.id.btnCancel)
        imageViewUsuario = findViewById(R.id.imageViewUsuario)




        /**
         * Sección para elegir una imagen de la galeria
         */
        btnGaleria.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {

                val pickImageIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                pickImageIntent.type = "image/*"
                startActivityForResult(pickImageIntent, PICK_IMAGE_REQUEST)

            }
        }


        /**
         * Btn camara
         */
        btnCamara.setOnClickListener {
            val intnentBtn = Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA)
            startActivity(intnentBtn)
        }

        /**
         * Btn cancelar
         */
        btnCancel.setOnClickListener {
            val intentBtn = Intent(this, MenuRunas::class.java)
            intentBtn.putExtra("id_usuario", id_usuario);
            startActivity(intentBtn)
            finish()
        }

        /**
         * Btn Guardar
         */
        btnSave.setOnClickListener {
            val intentListaRunas = Intent(this, MenuRunas::class.java);
            usuario = editTextUser.text.toString()
            var imgResult = true
            var usuarioResult = true
            var contraResult = true
            if (bitmapToByteArray(imagenRic!!) != elUsuario?.imagen){
                imgResult = !cambiarImagen()
            }
            if(editTextUser.text.isNotEmpty() && usuario != elUsuario?.usuario ){
                usuarioResult = !cambiarNombreUser()
                println(usuarioResult)
            }
            if(editTextPass.text.isNotEmpty() && editTextPass.text.toString() != elUsuario?.contrasenya){
                contraResult = !cambiarContra()
            }

            if (imgResult && usuarioResult && contraResult) {
                println("¡Los cambios se guardaron correctamente!")
                intentListaRunas.putExtra("id_usuario", id_usuario);
                startActivity(intentListaRunas);
                finish()
            }
        }

    }

    private fun cambiarImagen(): Boolean {
        database = UsuarioDatabase(this)
        var resultado: Boolean = false
        GlobalScope.launch(Dispatchers.IO) {
            val filasActualizadas =
                database.usuarioDao().updateUserImage(id_usuario, bitmapToByteArray(imagenRic!!))
            if (filasActualizadas > 0) {
                // La actualización se realizó con éxito
                // Hacer algo aquí, como mostrar un mensaje de éxito
                resultado = true
                showToast("Imagen cambiada con exito")
            } else {
                // No se actualizó ninguna fila, es posible que no haya ningún usuario con el ID proporcionado
                // o que la imagen proporcionada sea la misma que la existente
                // Hacer algo aquí, como mostrar un mensaje de error
                resultado = false
                showToast("Error al cambiar con exito")
            }
        }
        return resultado
    }

    private fun cambiarContra(): Boolean{
        database = UsuarioDatabase(this)
        var resultado: Boolean = false
        if (comprobarContra(editTextPass.text.toString(), editTextPassAgain.text.toString())) {
            GlobalScope.launch(Dispatchers.IO) {
                val filasActualizadas =
                    database.usuarioDao().updateUserName(id_usuario,usuario)
                if (filasActualizadas > 0) {
                    // La actualización se realizó con éxito
                    // Hacer algo aquí, como mostrar un mensaje de éxito
                    resultado = true
                    showToast("Contra cambiada con exito")
                } else {
                    // No se actualizó ninguna fila, es posible que no haya ningún usuario con el ID proporcionado
                    // o que la imagen proporcionada sea la misma que la existente
                    // Hacer algo aquí, como mostrar un mensaje de error
                    resultado = false
                    showToast("Error al cambiar con exito")
                }
            }
        } else {
            showToast("Las contraseñas no coinciden.")
        }
        return  resultado
    }

    private fun cambiarNombreUser(): Boolean{
        database = UsuarioDatabase(this)
        var resultado: Boolean = false
        GlobalScope.launch(Dispatchers.IO) {
            val filasActualizadas =
                database.usuarioDao().updateUserName(id_usuario,editTextUser.text.toString())
            if (filasActualizadas > 0) {
                // La actualización se realizó con éxito
                // Hacer algo aquí, como mostrar un mensaje de éxito
                resultado = true
                showToast("Nombre cambiada con exito")
            } else {
                // No se actualizó ninguna fila, es posible que no haya ningún usuario con el ID proporcionado
                // o que la imagen proporcionada sea la misma que la existente
                // Hacer algo aquí, como mostrar un mensaje de error
                resultado = false
                showToast("Error al cambiar con exito")
            }
        }
        return resultado
    }

    private fun comprobarContra(pass:String, passAgain:String):Boolean{
        return pass == passAgain
    }

    /**
     * Controla que coja bien la imagen
     * */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {

            data?.data?.let { selectedImageUri ->

                CoroutineScope(Dispatchers.IO).launch {

                    contentResolver.openInputStream(selectedImageUri)?.use { inputStream ->

                        imagenRic = BitmapFactory.decodeStream(inputStream)


                    }
                }

            }
            imageViewUsuario.setImageBitmap(imagenRic)
        }
    }

    /**
     * showToast
     */
    private fun showToast(text:String){
        runOnUiThread {
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Función para transformar una imagen a un array de bytes
     */

    fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream)
        return outputStream.toByteArray()
    }

    /**
     * Función para la transformación de un array de bytes a una imagen
     */

    fun byteArrayToBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

}