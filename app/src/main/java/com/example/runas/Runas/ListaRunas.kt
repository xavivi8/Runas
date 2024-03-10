package com.example.runas.Runas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.runas.R

class ListaRunas : AppCompatActivity() {

    lateinit var textViewID: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_runas)
        // Encuentra la referencia del TextView mediante su ID definido en el layout XML
        textViewID = findViewById(R.id.textViewID)
        var id: Long = 0
        id = intent.getLongExtra("id_usuario", 500)
        // Establece el texto del TextView con el valor del ID de usuario
        textViewID.text = "Id de Usuario: $id"
    }
}