package com.example.runas.DBControler

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "runas")
class Runas (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id_runa: Long? = null,

    @ColumnInfo(name = "id_user")
    val id_usuario: Long,

    @ColumnInfo(name = "name ")
    val nombre: String,

    @ColumnInfo(name = "main_rune ")
    val runaPrincipal: String,

    @ColumnInfo(name = "main_sub_rune ")
    val subRunasPrincipal: String,

    @ColumnInfo(name = "secondary_rune ")
    val runaSecundaria: String,

    @ColumnInfo(name = "secondary_sub_rune ")
    val subRunasSecundaria: String,

    @ColumnInfo(name = "additional_advantages ")
    val ventajasAdicionales: String,
){
    override fun toString(): String {

        return "ID Runa: "+id_runa +" Id Usuario: "+id_usuario+" Sus runas"
    }
}