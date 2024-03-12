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
import com.google.android.material.snackbar.Snackbar
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
        var lista: List<Runas> = emptyList()


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
            mostrarRunasAnteriores(lista)
            mostrarRunasAnteriores(lista)
        }

        /**
         * Btn adelante
         */
        btnAlante.setOnClickListener {
            mostrarSiguientesRunas(lista)
            mostrarSiguientesRunas(lista)
        }

        /**
         * Btn btnRuna1
         */
        btnRuna1.setOnClickListener {
            if (btnRuna1.text.isEmpty()) {
                Snackbar.make(btnRuna1, "El texto de btnRuna1 está vacío", Snackbar.LENGTH_SHORT).show()
            } else {
                val intentBtn = Intent(this, EditViewRuna::class.java)
                intentBtn.putExtra("id_usuario", id_usuario);
                intentBtn.putExtra("id_runa", idBtn1);
                startActivity(intentBtn)
                finish()
            }
        }

        /**
         * Btn btnRuna2
         */
        btnRuna2.setOnClickListener {
            if (btnRuna2.text.isEmpty()) {
                Snackbar.make(btnRuna2, "El texto de btnRuna2 está vacío", Snackbar.LENGTH_SHORT).show()
            } else {
                val intentBtn = Intent(this, EditViewRuna::class.java)
                intentBtn.putExtra("id_usuario", id_usuario);
                intentBtn.putExtra("id_runa", idBtn2);
                startActivity(intentBtn)
                finish()
            }
        }

        /**
         * Btn btnRuna3
         */
        btnRuna3.setOnClickListener {
            if (btnRuna3.text.isEmpty()) {
                Snackbar.make(btnRuna3, "El texto de btnRuna3 está vacío", Snackbar.LENGTH_SHORT).show()
            } else {
                val intentBtn = Intent(this, EditViewRuna::class.java)
                intentBtn.putExtra("id_usuario", id_usuario);
                intentBtn.putExtra("id_runa", idBtn3);
                startActivity(intentBtn)
                finish()
            }
        }

        /**
         * Btn btnRuna4
         */
        btnRuna4.setOnClickListener {
            if (btnRuna4.text.isEmpty()) {
                Snackbar.make(btnRuna4, "El texto de btnRuna3 está vacío", Snackbar.LENGTH_SHORT).show()
            } else {
                val intentBtn = Intent(this, EditViewRuna::class.java)
                intentBtn.putExtra("id_usuario", id_usuario);
                intentBtn.putExtra("id_runa", idBtn4);
                startActivity(intentBtn)
                finish()
            }
        }
    }

    private fun mostrarSiguientesRunas(runasList: List<Runas>) {
        if (runasList.isNotEmpty()) {
            val lastIndex = minOf(poscicionLista + 4, runasList.size)
            if (lastIndex - poscicionLista < 4) {
                // Mostrar Snackbar indicando que no hay suficientes runas para avanzar
                Snackbar.make(btnAlante, "No hay suficientes runas para avanzar", Snackbar.LENGTH_SHORT).show()
                return
            }
            for (i in poscicionLista until lastIndex) {
                val runa = runasList[i]
                when (i - poscicionLista + 1) {
                    1 -> {
                        btnRuna1.text = runa.id_runa.toString()
                        if(runa.id_runa != null){
                            idBtn1 = runa.id_runa
                        }
                    }
                    2 -> {
                        btnRuna2.text = runa.id_runa.toString()
                        if(runa.id_runa != null){
                            idBtn2 = runa.id_runa
                        }
                    }
                    3 -> {
                        btnRuna3.text = runa.id_runa.toString()
                        if(runa.id_runa != null){
                            idBtn3 = runa.id_runa
                        }
                    }
                    4 -> {
                        btnRuna4.text = runa.id_runa.toString()
                        if(runa.id_runa != null){
                            idBtn4 = runa.id_runa
                        }
                    }
                }
            }
            poscicionLista += 4
        } else {
            // Si la lista está vacía, establecer el texto de los botones como vacío o algún mensaje indicando que no hay runas disponibles
            btnRuna1.text = ""
            btnRuna2.text = ""
            btnRuna3.text = ""
            btnRuna4.text = ""
        }
    }


    private fun mostrarRunasAnteriores(runasList: List<Runas>) {
        if (runasList.isNotEmpty() && poscicionLista >= 4) {
            val startIndex = maxOf(poscicionLista - 4, 0) // Obtener el índice de inicio para las 4 runas anteriores
            val endIndex = poscicionLista // Calcular el índice del último elemento a mostrar (la posición actual)
            for (i in startIndex until endIndex) {
                val runa = runasList[i]
                when (i - startIndex + 1) { // Ajustar el índice para mostrar desde 1 hasta 4
                    1 -> {
                        btnRuna1.text = runa.id_runa.toString()
                        if (runa.id_runa != null) {
                            idBtn1 = runa.id_runa
                        }
                    }
                    2 -> {
                        btnRuna2.text = runa.id_runa.toString()
                        if (runa.id_runa != null) {
                            idBtn2 = runa.id_runa
                        }
                    }
                    3 -> {
                        btnRuna3.text = runa.id_runa.toString()
                        if (runa.id_runa != null) {
                            idBtn3 = runa.id_runa
                        }
                    }
                    4 -> {
                        btnRuna4.text = runa.id_runa.toString()
                        if (runa.id_runa != null) {
                            idBtn4 = runa.id_runa
                        }
                    }
                }
            }
            poscicionLista -= 4
        } else {
            // Mostrar Snackbar indicando que no hay suficientes runas para retroceder
            Snackbar.make(btnAtras, "No hay suficientes runas para retroceder", Snackbar.LENGTH_SHORT).show()
        }
    }


}