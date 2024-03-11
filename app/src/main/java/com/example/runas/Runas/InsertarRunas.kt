package com.example.runas.Runas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.ArrayAdapter
import com.example.runas.R

class InsertarRunas : AppCompatActivity() {
    val va: Long = 1

    lateinit var spinnerRunasPrin: Spinner
    lateinit var spinnerRunasPrin1: Spinner
    lateinit var spinnerRunasPrin2: Spinner
    lateinit var spinnerRunasPrin3: Spinner
    lateinit var spinnerRunasPrin4: Spinner
    lateinit var spinnerRunasSecun: Spinner
    private val runasArray by lazy { resources.getStringArray(R.array.runasPrincipales) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertar_runas)

        // Inicializar los Spinners
        spinnerRunasPrin = findViewById(R.id.spinnerRunasPrin)
        spinnerRunasPrin1 = findViewById(R.id.spinnerRunasPrin1)
        spinnerRunasPrin2 = findViewById(R.id.spinnerRunasPrin2)
        spinnerRunasPrin3 = findViewById(R.id.spinnerRunasPrin3)
        spinnerRunasPrin4 = findViewById(R.id.spinnerRunasPrin4)
        spinnerRunasSecun = findViewById(R.id.spinnerRunasSecun)

        // Obtener el array de opciones desde los recursos
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, runasArray)
        spinnerRunasPrin.adapter = adapter

        spinnerRunasPrin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                val selectedValue = parent?.getItemAtPosition(position).toString()
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
    }
}