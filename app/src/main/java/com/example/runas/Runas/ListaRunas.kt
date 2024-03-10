package com.example.runas.Runas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.runas.DBControler.Runas
import com.example.runas.DBControler.RunasDatabase
import com.example.runas.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListaRunas : AppCompatActivity() {

    var poscicionLista: Int = 0
    lateinit var database: RunasDatabase
    lateinit var textViewID: TextView
    lateinit var btnRuna1: Button
    lateinit var btnRuna2: Button
    lateinit var btnRuna3: Button
    lateinit var btnRuna4: Button
    lateinit var btnPerfil: Button
    lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_runas)
        database = RunasDatabase(this);
        btnRuna1 = findViewById(R.id.btnRuna1)
        btnRuna2 = findViewById(R.id.btnRuna2)
        btnRuna3 = findViewById(R.id.btnRuna3)
        btnRuna4 = findViewById(R.id.btnRuna4)
        btnPerfil = findViewById(R.id.btnPerfil)
        btnLogin = findViewById(R.id.btnLogin)
        var lista: List<Runas>


        // Encuentra la referencia del TextView mediante su ID definido en el layout XML
        textViewID = findViewById(R.id.textViewID)
        var id: Long = 0
        id = intent.getLongExtra("id_usuario", 500)
        // Establece el texto del TextView con el valor del ID de usuario
        textViewID.text = "Id de Usuario: $id"

        if (id != 0L && id != 500L) {
            GlobalScope.launch(Dispatchers.Main) {
                lista = database.runasDao().obtenerRunasPorUsuario(id)
                mostrarSiguientesRunas(lista)
            }
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