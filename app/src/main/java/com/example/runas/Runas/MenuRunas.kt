package com.example.runas.Runas

import android.content.ClipData.Item
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.runas.DBControler.RunasDatabase
import com.example.runas.DBControler.UsuarioDatabase
import com.example.runas.Login.Login
import com.example.runas.Perfil.Perfil
import com.example.runas.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

class MenuRunas : AppCompatActivity() {

    var id_usuario: Long = 1

    lateinit var database: RunasDatabase
    lateinit var databaseUser: UsuarioDatabase
    lateinit var textViewID: TextView
    lateinit var imageView: ImageView
    lateinit var btnPerfil: Button
    lateinit var btnLogin: Button
    lateinit var btnAgregarRuna: Button
    lateinit var btnListRuna: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_runas)
        database = RunasDatabase(this)
        databaseUser = UsuarioDatabase(this)
        imageView = findViewById(R.id.imageView)
        val inetntBtnLogin = Intent(this, Login::class.java)
        textViewID = findViewById(R.id.textViewID)
        id_usuario = intent.getLongExtra("id_usuario", 500)
        //textViewID.text = "Id de Usuario: $id_usuario"
        if (id_usuario != 0L && id_usuario != 500L) {
            GlobalScope.launch(Dispatchers.Main) {
                val usuarioTemporal = databaseUser.usuarioDao().getUserById(id_usuario)
                if (usuarioTemporal != null) {
                    usuarioTemporal.imagen?.let { // Comprobación de nulabilidad usando elvis operator
                        imageView.setImageBitmap(byteArrayToBitmap(it))
                    }
                } else {
                    startActivity(inetntBtnLogin)
                    showToast("Error al coger el usuario")
                }
            }
        } else {
            startActivity(inetntBtnLogin)
        }


        /* Botón login */
        btnLogin  = findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener {
            val inetntBtn = Intent(this, Login::class.java)
            startActivity(inetntBtn)
        }

        /* Botón Agregar runa */
        btnAgregarRuna = findViewById(R.id.btnAgregarRuna)
        btnAgregarRuna.setOnClickListener {
            val inetntBtn = Intent(this, InsertarRunas::class.java)
            inetntBtn.putExtra("id_usuario", id_usuario);
            startActivity(inetntBtn)
            finish()
        }

        /* Botón listar runas */
        btnListRuna = findViewById(R.id.btnListRuna)
        btnListRuna.setOnClickListener {
            val inetntBtn = Intent(this, ListaRunas::class.java)
            inetntBtn.putExtra("id_usuario", id_usuario);
            startActivity(inetntBtn)
        }

        /* Boton perfil */
        btnPerfil = findViewById(R.id.btnPerfil)
        btnPerfil.setOnClickListener {
            val inetntBtn = Intent(this, Perfil::class.java)
            inetntBtn.putExtra("id_usuario", id_usuario);
            startActivity(inetntBtn)
            finish()
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        // Asociamos el menú mediante el fichero de recurso
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.addRuna -> {
                // Crear un Intent para iniciar la nueva actividad
                val inetntBtn = Intent(this, InsertarRunas::class.java)
                inetntBtn.putExtra("id_usuario", id_usuario);
                startActivity(inetntBtn)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}