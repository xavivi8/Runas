package com.example.runas.Runas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.runas.DBControler.Runas
import com.example.runas.DBControler.RunasDatabase
import com.example.runas.Login.Login
import com.example.runas.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class InsertarRunas : AppCompatActivity() {
    var id_usuario: Long = 1
    var nombre: String = ""
    var subRunasPrincipal: String = ""
    var subRunasSecundaria: String = ""
    var ventajasAdicionales: String = ""
    var runaPrincipal: String = ""
    var runaPrincipal1: String = ""
    var runaPrincipal2: String = ""
    var runaPrincipal3: String = ""
    var runaPrincipal4: String = ""
    var runaSecundaria: String = ""
    var runaSecundaria1: String = ""
    var runaSecundaria2: String = ""
    var subRuna1: String = ""
    var subRuna2: String = ""
    var subRuna3: String = ""

    lateinit var database: RunasDatabase
    lateinit var spinnerRunasPrin: Spinner
    lateinit var spinnerRunasPrin1: Spinner
    lateinit var spinnerRunasPrin2: Spinner
    lateinit var spinnerRunasPrin3: Spinner
    lateinit var spinnerRunasPrin4: Spinner
    lateinit var spinnerRunasSecun: Spinner
    lateinit var spinnerRunasSecun1: Spinner
    lateinit var spinnerRunasSecun2: Spinner
    lateinit var spinnerSubRunas1: Spinner
    lateinit var spinnerSubRunas2: Spinner
    lateinit var spinnerSubRunas3: Spinner
    lateinit var edittextName: EditText
    lateinit var btnCancel: Button
    lateinit var btnSave: Button
    private val runasArray by lazy { resources.getStringArray(R.array.runasPrincipales) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertar_runas)
        database = RunasDatabase(this)

        // Inicializar los Spinners
        spinnerRunasPrin = findViewById(R.id.spinnerRunasPrin)
        spinnerRunasPrin1 = findViewById(R.id.spinnerRunasPrin1)
        spinnerRunasPrin2 = findViewById(R.id.spinnerRunasPrin2)
        spinnerRunasPrin3 = findViewById(R.id.spinnerRunasPrin3)
        spinnerRunasPrin4 = findViewById(R.id.spinnerRunasPrin4)
        spinnerRunasSecun = findViewById(R.id.spinnerRunasSecun)
        spinnerRunasSecun1 = findViewById(R.id.spinnerRunasSecun1)
        spinnerRunasSecun2 = findViewById(R.id.spinnerRunasSecun2)
        spinnerSubRunas1 = findViewById(R.id.spinnerSubRunas1)
        spinnerSubRunas2 = findViewById(R.id.spinnerSubRunas2)
        spinnerSubRunas3 = findViewById(R.id.spinnerSubRunas3)
        edittextName = findViewById(R.id.edittextName)
        btnCancel = findViewById(R.id.btnCancel)
        btnSave = findViewById(R.id.btnSave)

        /**/
        id_usuario = intent.getLongExtra("id_usuario", 500)
        if (id_usuario != 0L && id_usuario != 500L) {

        } else {
            val inetntBtn = Intent(this, Login::class.java)
            startActivity(inetntBtn)
        }

        // Obtener el valor del EditText edittextName
        edittextName = findViewById(R.id.edittextName)
        nombre = edittextName.text.toString()

        /* inicializar variables */
        runaPrincipal = getString(R.string.precision)
        runaPrincipal1 = getString(R.string.ataque_intensificado)
        runaPrincipal2 = getString(R.string.supercoracion)
        runaPrincipal3 = getString(R.string.leyenda_presteza)
        runaPrincipal4 = getString(R.string.golpe_de_gracia)
        runaSecundaria = getString(R.string.dominacion)
        runaSecundaria1 = getString(R.string.golpe_bajo)
        runaSecundaria2 = getString(R.string.sabor_a_sangre)
        subRuna1 = getString(R.string.fuerza_adaptable)
        subRuna2 = getString(R.string.fuerza_adaptable)
        subRuna3 = getString(R.string.vida)

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
         * Btn save
         */
        btnSave.setOnClickListener {
            val intentBtn = Intent(this, MenuRunas::class.java)
            intentBtn.putExtra("id_usuario", id_usuario);
            GlobalScope.launch(Dispatchers.Main) {
                val idInsertado = guardarRuna().await()
                if (idInsertado >= 0) {
                    startActivity(intentBtn)
                    finish()
                }
            }
        }

        /**
         * Spinner spinnerSubRunas1
         */
        val adapterSpinnerSubRunas1 = ArrayAdapter(this, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.spinnerSubRunas1))
        adapterSpinnerSubRunas1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSubRunas1.adapter = adapterSpinnerSubRunas1
        spinnerSubRunas1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                val selectedValue = parent?.getItemAtPosition(position).toString()
                subRuna1 = selectedValue
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Implementación opcional para cuando no se selecciona nada
            }
        }

        /**
         * Spinner spinnerSubRunas2
         */
        val adapterSpinnerSubRunas2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.spinnerSubRunas2))
        adapterSpinnerSubRunas2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSubRunas2.adapter = adapterSpinnerSubRunas2
        spinnerSubRunas2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                val selectedValue = parent?.getItemAtPosition(position).toString()
                subRuna2 = selectedValue
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Implementación opcional para cuando no se selecciona nada
            }
        }

        /**
         * Spinner spinnerSubRunas3
         */
        val adapterSpinnerSubRunas3 = ArrayAdapter(this, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.spinnerSubRunas3))
        adapterSpinnerSubRunas3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSubRunas3.adapter = adapterSpinnerSubRunas3
        spinnerSubRunas3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                val selectedValue = parent?.getItemAtPosition(position).toString()
                subRuna3 = selectedValue
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Implementación opcional para cuando no se selecciona nada
            }
        }

        /**
         * Spinner de las runas principal
         */
        // Obtener el array de opciones desde los recursos
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, runasArray)
        spinnerRunasPrin.adapter = adapter
        spinnerRunasPrin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                val selectedValue = parent?.getItemAtPosition(position).toString()
                runaPrincipal = selectedValue
                when(selectedValue) {
                    getString(R.string.precision) -> {
                        val adapter1 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.runasPrecision1))
                        val adapter2 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.runasPrecision2))
                        val adapter3 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.runasPrecision3))
                        val adapter4 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.runasPrecision4))
                        spinnerRunasPrin1.adapter = adapter1
                        spinnerRunasPrin2.adapter = adapter2
                        spinnerRunasPrin3.adapter = adapter3
                        spinnerRunasPrin4.adapter = adapter4
                    }
                    getString(R.string.dominacion) -> {
                        val adapter1 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.runasDominacion1))
                        val adapter2 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.runasDominacion2))
                        val adapter3 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.runasDominacion3))
                        val adapter4 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.runasDominacion4))
                        spinnerRunasPrin1.adapter = adapter1
                        spinnerRunasPrin2.adapter = adapter2
                        spinnerRunasPrin3.adapter = adapter3
                        spinnerRunasPrin4.adapter = adapter4
                    }
                    getString(R.string.brujeria) -> {
                        val adapter1 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.runasBrujeria1))
                        val adapter2 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.runasBrujeria2))
                        val adapter3 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.runasBrujeria3))
                        val adapter4 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.runasBrujeria4))
                        spinnerRunasPrin1.adapter = adapter1
                        spinnerRunasPrin2.adapter = adapter2
                        spinnerRunasPrin3.adapter = adapter3
                        spinnerRunasPrin4.adapter = adapter4
                    }
                    getString(R.string.valor) -> {
                        val adapter1 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.runasValor1))
                        val adapter2 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.runasValor2))
                        val adapter3 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.runasValor3))
                        val adapter4 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.runasValor4))
                        spinnerRunasPrin1.adapter = adapter1
                        spinnerRunasPrin2.adapter = adapter2
                        spinnerRunasPrin3.adapter = adapter3
                        spinnerRunasPrin4.adapter = adapter4
                    }
                    getString(R.string.inspiracion) -> {
                        val adapter1 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.runasInspiracion1))
                        val adapter2 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.runasInspiracion2))
                        val adapter3 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.runasInspiracion3))
                        val adapter4 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.runasInspiracion4))
                        spinnerRunasPrin1.adapter = adapter1
                        spinnerRunasPrin2.adapter = adapter2
                        spinnerRunasPrin3.adapter = adapter3
                        spinnerRunasPrin4.adapter = adapter4
                    }
                }
                val updatedRunasArray = runasArray.filter { it != selectedValue }
                val updatedAdapter = ArrayAdapter(
                    applicationContext,
                    android.R.layout.simple_spinner_item,
                    updatedRunasArray
                )
                spinnerRunasSecun.adapter = updatedAdapter
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Implementación opcional para cuando no se selecciona nada
            }
        }
        /**
         * Runas principales
         */
        spinnerRunasPrin1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long){
                val selectedValue = parent?.getItemAtPosition(position).toString()
                runaPrincipal1 = selectedValue
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Implementación opcional para cuando no se selecciona nada
            }
        }

        spinnerRunasPrin2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long){
                val selectedValue = parent?.getItemAtPosition(position).toString()
                runaPrincipal2 = selectedValue
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Implementación opcional para cuando no se selecciona nada
            }
        }

        spinnerRunasPrin3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long){
                val selectedValue = parent?.getItemAtPosition(position).toString()
                runaPrincipal3 = selectedValue
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Implementación opcional para cuando no se selecciona nada
            }
        }

        spinnerRunasPrin4.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long){
                val selectedValue = parent?.getItemAtPosition(position).toString()
                runaPrincipal4 = selectedValue
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Implementación opcional para cuando no se selecciona nada
            }
        }

        /**
         * Spinner de las sunas secundarias
         */
        spinnerRunasSecun.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                val selectedValue = parent?.getItemAtPosition(position).toString()
                runaSecundaria = selectedValue
                when(selectedValue) {
                    getString(R.string.precision) -> {
                        val adapter1 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.runasSecunPrecision))
                        spinnerRunasSecun1.adapter = adapter1
                    }
                    getString(R.string.dominacion) -> {
                        val adapter1 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.runasSecunDominacion))
                        spinnerRunasSecun1.adapter = adapter1
                    }
                    getString(R.string.brujeria) -> {
                        val adapter1 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.runasSecunBrujeria))
                        spinnerRunasSecun1.adapter = adapter1
                    }
                    getString(R.string.valor) -> {
                        val adapter1 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.runasSecunValor))
                        spinnerRunasSecun1.adapter = adapter1
                    }
                    getString(R.string.inspiracion) -> {
                        val adapter1 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.runasSecunInspiracion))
                        spinnerRunasSecun1.adapter = adapter1
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Implementación opcional para cuando no se selecciona nada
            }
        }

        /**
         *
         */
        spinnerRunasSecun1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                val selectedValue = parent?.getItemAtPosition(position).toString()
                runaSecundaria1 = selectedValue
                // Obtener los valores del spinnerRunasSecun1 sin la selección actual
                val valuesWithoutSelection = obtenerValoresSinSeleccion(spinnerRunasSecun1, selectedValue)

                // Configurar el adapter y asignarlo al spinnerRunasSecun2
                val adapter2 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, valuesWithoutSelection)
                spinnerRunasSecun2.adapter = adapter2
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Implementación opcional para cuando no se selecciona nada
            }
        }

        spinnerRunasSecun2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long){
                val selectedValue = parent?.getItemAtPosition(position).toString()
                runaSecundaria2 = selectedValue
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Implementación opcional para cuando no se selecciona nada
            }
        }
    }
    private fun obtenerValoresSinSeleccion(spinner: Spinner, selectedValue: String): Array<String> {
        val adapter = spinner.adapter as ArrayAdapter<String>
        val values = mutableListOf<String>()
        for (i in 0 until adapter.count) {
            val value = adapter.getItem(i) ?: continue
            if (value != selectedValue) {
                values.add(value)
            }
        }
        return values.toTypedArray()
    }

    /**
     * guardarRuna se ha convertido en una función asincrona para evitar un bloqueo en el hilo principal
     */
    private fun guardarRuna(): CompletableDeferred<Long> {
        database = RunasDatabase(this)
        val deferred = CompletableDeferred<Long>()
        nombre = edittextName.text.toString()
        if (comprobarValores() == true){
            val nuevaRuna = Runas(
                id_usuario = id_usuario,
                nombre = nombre,
                runaPrincipal = runaPrincipal,
                subRunasPrincipal = "$runaPrincipal1,$runaPrincipal2,$runaPrincipal3,$runaPrincipal4",
                runaSecundaria = runaSecundaria,
                subRunasSecundaria = "$runaSecundaria1,$runaSecundaria2",
                ventajasAdicionales = "$subRuna1,$subRuna2,$subRuna3"
            )
            // Usar CompletableDeferred para esperar el resultado de la inserción


            // Insertar la nueva instancia de Runas en la base de datos
            GlobalScope.launch(Dispatchers.IO) {
                val idInsertado: Long = database.runasDao().insertRunas(nuevaRuna)
                if (idInsertado >= 0) {
                    Snackbar.make(findViewById(android.R.id.content), "Inserción correcta. ID: $idInsertado Nombre de la página de runas $nombre", Snackbar.LENGTH_SHORT).show()
                    deferred.complete(idInsertado)
                } else {
                    Snackbar.make(findViewById(android.R.id.content), "Inserción incorrecta $idInsertado", Snackbar.LENGTH_SHORT).show()
                    deferred.complete(0)
                }
            }

        } else {
            Snackbar.make(findViewById(android.R.id.content), "Algun valor falta por rellenar", Snackbar.LENGTH_SHORT).show()
            deferred.complete(0)
        }
        return deferred // Esperar y devolver el resultado
    }

    private fun comprobarValores(): Boolean {
        return !(nombre.isNullOrEmpty() ||
                runaPrincipal.isNullOrEmpty() ||
                runaPrincipal1.isNullOrEmpty() ||
                runaPrincipal2.isNullOrEmpty() ||
                runaPrincipal3.isNullOrEmpty() ||
                runaPrincipal4.isNullOrEmpty() ||
                runaSecundaria.isNullOrEmpty() ||
                runaSecundaria1.isNullOrEmpty() ||
                runaSecundaria2.isNullOrEmpty() ||
                subRuna1.isNullOrEmpty() ||
                subRuna2.isNullOrEmpty() ||
                subRuna3.isNullOrEmpty())
    }
}