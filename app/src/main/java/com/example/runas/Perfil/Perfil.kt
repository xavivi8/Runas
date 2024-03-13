package com.example.runas.Perfil

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.example.runas.Login.Login
import com.example.runas.R
import com.example.runas.Runas.MenuRunas
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Perfil : AppCompatActivity() {

    var imageUri: Uri? = null
    var usuario: String = ""
    var contrasenya: String = ""

    var entrada = false
    var id_usuario: Long = -1
    private val PICK_IMAGE_REQUEST = 1

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

        id_usuario = intent.getLongExtra("id_usuario", 500)
        if (id_usuario != 0L && id_usuario != 500L) {
            GlobalScope.launch(Dispatchers.Main) {

            }
        } else {
            val inetntBtn = Intent(this, Login::class.java)
            startActivity(inetntBtn)
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

        if (comprobarContra(editTextPass.text.toString(), editTextPassAgain.text.toString())) {

        }


        /**
         * Sección para elegir una imagen de la galeria
         */
        btnGaleria.setOnClickListener {

            val intentGal = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intentGal, PICK_IMAGE_REQUEST)
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

    }

    private fun comprobarContra(pass:String, passAgain:String):Boolean{
        return pass == passAgain
    }

    /**
     * Controla que coja bien la imagen
     * */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            entrada = true
            imageViewUsuario = findViewById(R.id.imageViewUsuario)
            imageUri = data.data
            uri = data.data!! /*Para que no de error con !! porque va a tener un dato si o si*/

            imageViewUsuario.setImageURI(imageUri)
        }
    }
}