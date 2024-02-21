package com.example.runas.Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.runas.DBControler.UsuarioDatabase
import com.example.runas.R

class Registro : AppCompatActivity() {
    lateinit var database: UsuarioDatabase
    lateinit var guardar: Button
    lateinit var cancelar: Button
    lateinit var user: EditText
    lateinit var pass: EditText
    lateinit var passAgain: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        guardar = findViewById(R.id.btnGuardar)
        cancelar = findViewById(R.id.btnCancelar)
        user = findViewById(R.id.editTextUser)
        pass = findViewById(R.id.editTextPass)
        passAgain = findViewById(R.id.editTextPassAgain)

        guardar.setOnClickListener {

            if (comprobarContra(pass.text.toString(), passAgain.text.toString())) {
                showToast("Las contraseñas coinciden.")
                finish()
            } else {
                showToast("Las contraseñas no coinciden.")
            }
        }

        cancelar.setOnClickListener {
            finish()
        }
    }

    private fun comprobarContra(pass:String, passAgain:String):Boolean{
        return pass == passAgain
    }

    private fun showToast(text:String){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}