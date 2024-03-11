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
    lateinit var spinnerRunasSecun: Spinner
    private val runasArray by lazy { resources.getStringArray(R.array.runasPrincipales) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertar_runas)

        // Inicializar los Spinners
        spinnerRunasPrin = findViewById(R.id.spinnerRunasPrin)
        spinnerRunasSecun = findViewById(R.id.spinnerRunasSecun)

        // Obtener el array de opciones desde los recursos
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, runasArray)
        spinnerRunasPrin.adapter = adapter

        spinnerRunasPrin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                val selectedValue = parent?.getItemAtPosition(position).toString()
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