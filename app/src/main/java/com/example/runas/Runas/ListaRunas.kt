package com.example.runas.Runas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.runas.DBControler.Runas
import com.example.runas.DBControler.RunasDatabase
import com.example.runas.Login.Login
import com.example.runas.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListaRunas : AppCompatActivity() {

    var id_usuario: Long = -1
    var poscicionLista: Int = 0
    var idBtn1: Long = 0
    var idBtn2: Long = 0
    var idBtn3: Long = 0
    var idBtn4: Long = 0

    lateinit var database: RunasDatabase
    lateinit var textViewID: TextView
    lateinit var btnRuna1: Button
    lateinit var btnRuna2: Button
    lateinit var btnRuna3: Button
    lateinit var btnRuna4: Button
    lateinit var btnAtras: Button
    lateinit var btnAlante: Button
    lateinit var btnMenu: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_runas)
        database = RunasDatabase(this);
        btnRuna1 = findViewById(R.id.btnRuna1)
        btnRuna2 = findViewById(R.id.btnRuna2)
        btnRuna3 = findViewById(R.id.btnRuna3)
        btnRuna4 = findViewById(R.id.btnRuna4)
        btnAtras = findViewById(R.id.btnAtras)
        btnAlante = findViewById(R.id.btnAlante)
        btnMenu = findViewById(R.id.btnMenu)
        var lista: List<Runas>


        // Encuentra la referencia del TextView mediante su ID definido en el layout XML
        textViewID = findViewById(R.id.textViewID)
        id_usuario = intent.getLongExtra("id_usuario", 500)
        // Establece el texto del TextView con el valor del ID de usuario
        textViewID.text = "Id de Usuario: $id_usuario"

        if (id_usuario != 0L && id_usuario != 500L) {
            GlobalScope.launch(Dispatchers.Main) {
                lista = database.runasDao().obtenerRunasPorUsuario(id_usuario)
                mostrarSiguientesRunas(lista)
            }
        } else {
            val inetntBtn = Intent(this, Login::class.java)
            startActivity(inetntBtn)
        }

        /**
         * Btn menu
         */
        btnMenu.setOnClickListener {
            val inetntBtn = Intent(this, MenuRunas::class.java)
            inetntBtn.putExtra("id_usuario", id_usuario);
            startActivity(inetntBtn)
            finish()
        }

        /**
         * Btn atras
         */
        btnAtras.setOnClickListener {

        }

        /**
         * Btn adelante
         */
        btnAlante.setOnClickListener {

        }
    }

    private fun mostrarSiguientesRunas(runasList: List<Runas>) {
        // Asegurarse de que poscicionLista esté dentro de los límites de la lista
        if (poscicionLista >= 0 && poscicionLista < runasList.size) {
            val lastIndex = minOf(poscicionLista + 4, runasList.size) // Último índice a considerar
            for (i in poscicionLista until lastIndex) {
                val runa = runasList[i]
                when (i - poscicionLista + 1) { // Se ajusta el índice para mostrar desde 1 hasta 4
                    1 -> btnRuna1.text = runa.id_runa.toString()
                    2 -> btnRuna2.text = runa.id_runa.toString()
                    3 -> btnRuna3.text = runa.id_runa.toString()
                    4 -> btnRuna4.text = runa.id_runa.toString()
                }
            }
        }
    }


}