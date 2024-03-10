package com.example.runas.DBControler

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "runas")
class Runas (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_runas")
    val id_runa: Long = 0,

    @ColumnInfo(name = "id_usuario")
    val id_usuario: Long,

    @ColumnInfo(name = "runaprincipal")
    val runaPrincipal: Long,

    @ColumnInfo(name = "subRunasPrincipal")
    val subRunasPrincipal: String,

    @ColumnInfo(name = "runasecundaria")
    val runaSecundaria: Long,

    @ColumnInfo(name = "subRunasSevundaria")
    val subRunasSevundaria: String,

    @ColumnInfo(name = "ventajasAdicionales")
    val ventajasAdicionales: String,
){
    override fun toString(): String {

        return "ID Runa: "+id_runa +" Id Usuario: "+id_usuario+" Sus runas"
    }
}