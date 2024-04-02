package com.example.runas.DBControler

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "users")
data class Usuario (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "user_name")
    val usuario: String,

    @ColumnInfo(name = "image")
    val imagen: ByteArray? = null,

    @ColumnInfo(name = "pass")
    val contrasenya: String,
){// Función redefinida para mostrar los datos correctamente
    override fun toString(): String {

        return "Usuario: "+usuario +" Contrasenya: "+contrasenya
    }
}