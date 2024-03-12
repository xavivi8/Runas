package com.example.runas.Runas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.example.runas.DBControler.Runas
import com.example.runas.DBControler.RunasDatabase
import com.example.runas.Login.Login
import com.example.runas.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditViewRuna : AppCompatActivity() {

    var id_usuario: Long = -1
    var id_runa: Long = -1
    var laRuna: Runas? = null
    var nombre: String = ""
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
        setContentView(R.layout.activity_edit_view_runa)
        database = RunasDatabase(this);

        id_runa = intent.getLongExtra("id_runa", 500)
        id_usuario = intent.getLongExtra("id_usuario", 500)
        if (id_usuario != 0L && id_usuario != 500L) {
            // Usamos withContext para esperar a que se complete la operación de la base de datos
            GlobalScope.launch(Dispatchers.Main) {
                laRuna = withContext(Dispatchers.IO) {
                    database.runasDao().obtenerRunaPorId(id_runa)
                }
                // Comprobar si laRuna es nula después de obtenerla
                if (laRuna == null) {
                    // Mostrar Snackbar indicando que no se encontró la runa
                    Snackbar.make(findViewById(android.R.id.content), "No se encontró la runa", Snackbar.LENGTH_SHORT).show()
                } else {
                    // Aquí puedes manejar laRuna después de que se haya obtenido de la base de datos
                    Snackbar.make(findViewById(android.R.id.content), "Tengo mi runa", Snackbar.LENGTH_SHORT).show()
                    // Inicializar los spinners con los valores de la runa obtenida
                    inicializarRuna(laRuna!!)
                }
            }
        } else {
            val inetntBtn = Intent(this, Login::class.java)
            startActivity(inetntBtn)
        }

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
            val intentBtn = Intent(this, ListaRunas::class.java)
            intentBtn.putExtra("id_usuario", id_usuario);
            startActivity(intentBtn)
            finish()
        }

        /**
         * Btn save
         */
        btnSave.setOnClickListener {

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
                subRuna3 = selectedValue
                runaPrincipal = selectedValue
                when(selectedValue) {
                    getString(R.string.precision) -> {
                        // Obtener los arrays de recursos
                        val array1 = resources.getStringArray(R.array.runasPrecision1)
                        val array2 = resources.getStringArray(R.array.runasPrecision2)
                        val array3 = resources.getStringArray(R.array.runasPrecision3)
                        val array4 = resources.getStringArray(R.array.runasPrecision4)

                        // Crear adaptadores para cada spinner
                        val adapter1 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, array1)
                        val adapter2 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, array2)
                        val adapter3 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, array3)
                        val adapter4 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, array4)

                        // Asignar los adaptadores a los spinners
                        spinnerRunasPrin1.adapter = adapter1
                        spinnerRunasPrin2.adapter = adapter2
                        spinnerRunasPrin3.adapter = adapter3
                        spinnerRunasPrin4.adapter = adapter4

                        // Seleccionar los valores por defecto
                        println("runaPrincipal1: $runaPrincipal1, array1: ${array1.joinToString()}")

                        val defaultPosition1 = array1.indexOf(runaPrincipal1)
                        println("defaultPosition1: $defaultPosition1")

                        if (defaultPosition1 != -1) {
                            spinnerRunasPrin1.setSelection(defaultPosition1)
                        }

                        val defaultPosition2 = array2.indexOf(runaPrincipal2)
                        if (defaultPosition2 != -1) spinnerRunasPrin2.setSelection(defaultPosition2)

                        val defaultPosition3 = array3.indexOf(runaPrincipal3)
                        if (defaultPosition3 != -1) spinnerRunasPrin3.setSelection(defaultPosition3)

                        val defaultPosition4 = array4.indexOf(runaPrincipal4)
                        if (defaultPosition4 != -1) spinnerRunasPrin4.setSelection(defaultPosition4)


                    }
                    getString(R.string.dominacion) -> {
                        // Obtener los arrays de recursos
                        val array1 = resources.getStringArray(R.array.runasDominacion1)
                        val array2 = resources.getStringArray(R.array.runasDominacion2)
                        val array3 = resources.getStringArray(R.array.runasDominacion3)
                        val array4 = resources.getStringArray(R.array.runasDominacion4)

                        // Crear adaptadores para cada spinner
                        val adapter1 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, array1)
                        val adapter2 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, array2)
                        val adapter3 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, array3)
                        val adapter4 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, array4)

                        // Asignar los adaptadores a los spinners
                        spinnerRunasPrin1.adapter = adapter1
                        spinnerRunasPrin2.adapter = adapter2
                        spinnerRunasPrin3.adapter = adapter3
                        spinnerRunasPrin4.adapter = adapter4

                        // Seleccionar los valores por defecto
                        println("runaPrincipal1: $runaPrincipal1, array1: ${array1.joinToString()}")

                        val defaultPosition1 = array1.indexOf(runaPrincipal1)
                        println("defaultPosition1: $defaultPosition1")

                        if (defaultPosition1 != -1) {
                            spinnerRunasPrin1.setSelection(defaultPosition1)
                        }

                        val defaultPosition2 = array2.indexOf(runaPrincipal2)
                        if (defaultPosition2 != -1) spinnerRunasPrin2.setSelection(defaultPosition2)

                        val defaultPosition3 = array3.indexOf(runaPrincipal3)
                        if (defaultPosition3 != -1) spinnerRunasPrin3.setSelection(defaultPosition3)

                        val defaultPosition4 = array4.indexOf(runaPrincipal4)
                        if (defaultPosition4 != -1) spinnerRunasPrin4.setSelection(defaultPosition4)
                    }
                    getString(R.string.brujeria) -> {
                        // Obtener los arrays de recursos
                        val array1 = resources.getStringArray(R.array.runasBrujeria1)
                        val array2 = resources.getStringArray(R.array.runasBrujeria2)
                        val array3 = resources.getStringArray(R.array.runasBrujeria3)
                        val array4 = resources.getStringArray(R.array.runasBrujeria4)

                        // Crear adaptadores para cada spinner
                        val adapter1 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, array1)
                        val adapter2 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, array2)
                        val adapter3 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, array3)
                        val adapter4 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, array4)

                        // Asignar los adaptadores a los spinners
                        spinnerRunasPrin1.adapter = adapter1
                        spinnerRunasPrin2.adapter = adapter2
                        spinnerRunasPrin3.adapter = adapter3
                        spinnerRunasPrin4.adapter = adapter4

                        // Seleccionar los valores por defecto
                        println("runaPrincipal1: $runaPrincipal1, array1: ${array1.joinToString()}")

                        val defaultPosition1 = array1.indexOf(runaPrincipal1)
                        println("defaultPosition1: $defaultPosition1")

                        if (defaultPosition1 != -1) {
                            spinnerRunasPrin1.setSelection(defaultPosition1)
                        }

                        val defaultPosition2 = array2.indexOf(runaPrincipal2)
                        if (defaultPosition2 != -1) spinnerRunasPrin2.setSelection(defaultPosition2)

                        val defaultPosition3 = array3.indexOf(runaPrincipal3)
                        if (defaultPosition3 != -1) spinnerRunasPrin3.setSelection(defaultPosition3)

                        val defaultPosition4 = array4.indexOf(runaPrincipal4)
                        if (defaultPosition4 != -1) spinnerRunasPrin4.setSelection(defaultPosition4)
                    }
                    getString(R.string.valor) -> {
                        // Obtener los arrays de recursos
                        val array1 = resources.getStringArray(R.array.runasValor1)
                        val array2 = resources.getStringArray(R.array.runasValor2)
                        val array3 = resources.getStringArray(R.array.runasValor3)
                        val array4 = resources.getStringArray(R.array.runasValor4)

                        // Crear adaptadores para cada spinner
                        val adapter1 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, array1)
                        val adapter2 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, array2)
                        val adapter3 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, array3)
                        val adapter4 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, array4)

                        // Asignar los adaptadores a los spinners
                        spinnerRunasPrin1.adapter = adapter1
                        spinnerRunasPrin2.adapter = adapter2
                        spinnerRunasPrin3.adapter = adapter3
                        spinnerRunasPrin4.adapter = adapter4

                        // Seleccionar los valores por defecto
                        println("runaPrincipal1: $runaPrincipal1, array1: ${array1.joinToString()}")

                        val defaultPosition1 = array1.indexOf(runaPrincipal1)
                        println("defaultPosition1: $defaultPosition1")

                        if (defaultPosition1 != -1) {
                            spinnerRunasPrin1.setSelection(defaultPosition1)
                        }

                        val defaultPosition2 = array2.indexOf(runaPrincipal2)
                        if (defaultPosition2 != -1) spinnerRunasPrin2.setSelection(defaultPosition2)

                        val defaultPosition3 = array3.indexOf(runaPrincipal3)
                        if (defaultPosition3 != -1) spinnerRunasPrin3.setSelection(defaultPosition3)

                        val defaultPosition4 = array4.indexOf(runaPrincipal4)
                        if (defaultPosition4 != -1) spinnerRunasPrin4.setSelection(defaultPosition4)
                    }
                    getString(R.string.inspiracion) -> {
                        // Obtener los arrays de recursos
                        val array1 = resources.getStringArray(R.array.runasInspiracion1)
                        val array2 = resources.getStringArray(R.array.runasInspiracion2)
                        val array3 = resources.getStringArray(R.array.runasInspiracion3)
                        val array4 = resources.getStringArray(R.array.runasInspiracion4)

                        // Crear adaptadores para cada spinner
                        val adapter1 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, array1)
                        val adapter2 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, array2)
                        val adapter3 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, array3)
                        val adapter4 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, array4)

                        // Asignar los adaptadores a los spinners
                        spinnerRunasPrin1.adapter = adapter1
                        spinnerRunasPrin2.adapter = adapter2
                        spinnerRunasPrin3.adapter = adapter3
                        spinnerRunasPrin4.adapter = adapter4


                        println("runaPrincipal1: $runaPrincipal1, array1: ${array1.joinToString()}")

                        val defaultPosition1 = array1.indexOf(runaPrincipal1)
                        println("defaultPosition1: $defaultPosition1")

                        if (defaultPosition1 != -1) {
                            spinnerRunasPrin1.setSelection(defaultPosition1)
                        }

                        val defaultPosition2 = array2.indexOf(runaPrincipal2)
                        if (defaultPosition2 != -1) spinnerRunasPrin2.setSelection(defaultPosition2)

                        val defaultPosition3 = array3.indexOf(runaPrincipal3)
                        if (defaultPosition3 != -1) spinnerRunasPrin3.setSelection(defaultPosition3)

                        val defaultPosition4 = array4.indexOf(runaPrincipal4)
                        if (defaultPosition4 != -1) spinnerRunasPrin4.setSelection(defaultPosition4)
                    }
                }
                val updatedRunasArray = runasArray.filter { it != selectedValue }
                val updatedAdapter = ArrayAdapter(
                    applicationContext,
                    android.R.layout.simple_spinner_item,
                    updatedRunasArray
                )
                spinnerRunasSecun.adapter = updatedAdapter

                // Verificar si runaSecundaria está presente en updatedRunasArray y establecer la selección si es así
                val indexSecun = updatedRunasArray.indexOf(runaSecundaria)
                if (indexSecun != -1) {
                    spinnerRunasSecun.setSelection(indexSecun)
                }

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
            var array1: Array<String> = arrayOf()
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                val selectedValue = parent?.getItemAtPosition(position).toString()
                runaSecundaria = selectedValue
                when(selectedValue) {
                    getString(R.string.precision) -> {
                        // Obtener los arrays de recursos
                        array1 = resources.getStringArray(R.array.runasSecunPrecision)

                        // Crear adaptadores para cada spinner
                        val adapter1 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, array1)

                        // Asignar los adaptadores a los spinners
                        spinnerRunasSecun1.adapter = adapter1

                        // Seleccionar los valores por defecto
                        val defaultPosition1 = array1.indexOf(runaSecundaria1)

                        if (defaultPosition1 != -1) {
                            spinnerRunasSecun1.setSelection(defaultPosition1)
                        }
                    }
                    getString(R.string.dominacion) -> {
                        // Obtener los arrays de recursos
                        array1 = resources.getStringArray(R.array.runasSecunDominacion)

                        // Crear adaptadores para cada spinner
                        val adapter1 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, array1)

                        // Asignar los adaptadores a los spinners
                        spinnerRunasSecun1.adapter = adapter1

                        // Seleccionar los valores por defecto
                        val defaultPosition1 = array1.indexOf(runaSecundaria1)

                        if (defaultPosition1 != -1) {
                            spinnerRunasSecun1.setSelection(defaultPosition1)
                        }
                    }
                    getString(R.string.brujeria) -> {
                        // Obtener los arrays de recursos
                        array1 = resources.getStringArray(R.array.runasSecunBrujeria)

                        // Crear adaptadores para cada spinner
                        val adapter1 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, array1)

                        // Asignar los adaptadores a los spinners
                        spinnerRunasSecun1.adapter = adapter1

                        // Seleccionar los valores por defecto
                        val defaultPosition1 = array1.indexOf(runaSecundaria1)

                        if (defaultPosition1 != -1) {
                            spinnerRunasSecun1.setSelection(defaultPosition1)
                        }
                    }
                    getString(R.string.valor) -> {
                        // Obtener los arrays de recursos
                        array1 = resources.getStringArray(R.array.runasSecunValor)

                        // Crear adaptadores para cada spinner
                        val adapter1 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, array1)

                        // Asignar los adaptadores a los spinners
                        spinnerRunasSecun1.adapter = adapter1

                        // Seleccionar los valores por defecto
                        val defaultPosition1 = array1.indexOf(runaSecundaria1)

                        if (defaultPosition1 != -1) {
                            spinnerRunasSecun1.setSelection(defaultPosition1)
                        }
                    }
                    getString(R.string.inspiracion) -> {
                        // Obtener los arrays de recursos
                        array1 = resources.getStringArray(R.array.runasSecunInspiracion)

                        // Crear adaptadores para cada spinner
                        val adapter1 = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, array1)

                        // Asignar los adaptadores a los spinners
                        spinnerRunasSecun1.adapter = adapter1

                        // Seleccionar los valores por defecto
                        val defaultPosition1 = array1.indexOf(runaSecundaria1)

                        if (defaultPosition1 != -1) {
                            spinnerRunasSecun1.setSelection(defaultPosition1)
                        }
                    }
                }
                // Código para seleccionar el valor coincidente por defecto en spinnerRunasSecun
                val matchingIndex = array1.indexOf(runaSecundaria)
                if (matchingIndex != -1) {
                    spinnerRunasSecun1.setSelection(matchingIndex)
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

                val indexSecun = valuesWithoutSelection.indexOf(runaSecundaria2)
                if (indexSecun != -1) {
                    spinnerRunasSecun2.setSelection(indexSecun)
                }
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

    private fun inicializarRuna(laRuna: Runas){
        val subRunasPrincipal = laRuna.subRunasPrincipal.split(",")
        val subRunasSecundaria = laRuna.subRunasSecundaria.split(",")
        val ventajasAdicionales = laRuna.ventajasAdicionales.split(",")
        nombre = laRuna.nombre

        // Asignar el nombre al EditText
        edittextName.setText(laRuna.nombre)

        // Asignar los valores a las variables
        runaPrincipal = laRuna.runaPrincipal
        runaPrincipal1 = subRunasPrincipal.getOrNull(0) ?: ""
        runaPrincipal2 = subRunasPrincipal.getOrNull(1) ?: ""
        runaPrincipal3 = subRunasPrincipal.getOrNull(2) ?: ""
        runaPrincipal4 = subRunasPrincipal.getOrNull(3) ?: ""
        runaSecundaria = laRuna.runaSecundaria
        runaSecundaria1 = subRunasSecundaria.getOrNull(0) ?: ""
        runaSecundaria2 = subRunasSecundaria.getOrNull(1) ?: ""
        subRuna1 = ventajasAdicionales.getOrNull(0) ?: ""
        subRuna2 = ventajasAdicionales.getOrNull(1) ?: ""
        subRuna3 = ventajasAdicionales.getOrNull(2) ?: ""

        println("runaPrincipal1: $subRunasPrincipal")
        println("runaPrincipal1: $runaPrincipal1")

        if (runaPrincipal.isNotEmpty()) {
            val newPosition = (spinnerRunasPrin.adapter as? ArrayAdapter<String>)?.getPosition(runaPrincipal)
            newPosition?.let { spinnerRunasPrin.setSelection(it) }
        }
        asignarRunasPrinSecundarias()
        if (runaSecundaria.isNotEmpty()) {
            val newPosition = (spinnerRunasSecun.adapter as? ArrayAdapter<String>)?.getPosition(runaSecundaria)
            newPosition?.let { spinnerRunasSecun.setSelection(it) }
        }
        asignarRunasSecunSecundarias()
        if (subRuna1.isNotEmpty()) {
            val newPosition = (spinnerSubRunas1.adapter as? ArrayAdapter<String>)?.getPosition(subRuna1)
            newPosition?.let { spinnerSubRunas1.setSelection(it) }
        }
        if (subRuna2.isNotEmpty()) {
            val newPosition = (spinnerSubRunas2.adapter as? ArrayAdapter<String>)?.getPosition(subRuna2)
            newPosition?.let { spinnerSubRunas2.setSelection(it) }
        }
        if (subRuna3.isNotEmpty()) {
            val newPosition = (spinnerSubRunas3.adapter as? ArrayAdapter<String>)?.getPosition(subRuna3)
            newPosition?.let { spinnerSubRunas3.setSelection(it) }
        }


    }

    private fun asignarRunasPrinSecundarias(){
        if (runaPrincipal1.isNotEmpty()) {
            val newPosition = (spinnerRunasPrin1.adapter as? ArrayAdapter<String>)?.getPosition(runaPrincipal1)
            newPosition?.let { spinnerRunasPrin1.setSelection(it) }
        }
        if (runaPrincipal2.isNotEmpty()) {
            val newPosition = (spinnerRunasPrin2.adapter as? ArrayAdapter<String>)?.getPosition(runaPrincipal2)
            newPosition?.let { spinnerRunasPrin2.setSelection(it) }
        }
        if (runaPrincipal3.isNotEmpty()) {
            val newPosition = (spinnerRunasPrin3.adapter as? ArrayAdapter<String>)?.getPosition(runaPrincipal3)
            newPosition?.let { spinnerRunasPrin3.setSelection(it) }
        }
        if (runaPrincipal4.isNotEmpty()) {
            val newPosition = (spinnerRunasPrin4.adapter as? ArrayAdapter<String>)?.getPosition(runaPrincipal4)
            newPosition?.let { spinnerRunasPrin4.setSelection(it) }
        }
    }

    private fun asignarRunasSecunSecundarias(){
        if (runaSecundaria1.isNotEmpty()) {
            val newPosition = (spinnerRunasSecun1.adapter as? ArrayAdapter<String>)?.getPosition(runaSecundaria1)
            newPosition?.let { spinnerRunasSecun1.setSelection(it) }
        }
        if (runaSecundaria2.isNotEmpty()) {
            val newPosition = (spinnerRunasSecun2.adapter as? ArrayAdapter<String>)?.getPosition(runaSecundaria2)
            newPosition?.let { spinnerRunasSecun2.setSelection(it) }
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