package com.example.runas.Runas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.runas.Login.Login
import com.example.runas.R

class MenuRunas : AppCompatActivity() {

    var id: Long = 0

    lateinit var textViewID: TextView
    lateinit var btnPerfil: Button
    lateinit var btnLogin: Button
    lateinit var btnAgregarRuna: Button
    lateinit var btnListRuna: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_runas)

        textViewID = findViewById(R.id.textViewID)
        id = intent.getLongExtra("id_usuario", 500)
        textViewID.text = "Id de Usuario: $id"
        if (id != 0L && id != 500L) {

        } else {
            val inetntBtn = Intent(this, Login::class.java)
            startActivity(inetntBtn)
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
            inetntBtn.putExtra("id_usuario", id);
            startActivity(inetntBtn)
        }

        /* Botón listar runas */
        btnListRuna = findViewById(R.id.btnListRuna)
        btnListRuna.setOnClickListener {
            val inetntBtn = Intent(this, ListaRunas::class.java)
            inetntBtn.putExtra("id_usuario", id);
            startActivity(inetntBtn)
        }

        /* Boton perfil */
        btnPerfil = findViewById(R.id.btnPerfil)
    }
}