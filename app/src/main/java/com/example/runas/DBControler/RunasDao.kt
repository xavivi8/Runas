package com.example.runas.DBControler

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface RunasDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarRunas(runas: Runas)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertRunas(runas: Runas): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarRunasConParametros(
        id_usuario: Long,
        runaPrincipal: Long,
        subRunasPrincipal: String,
        runaSecundaria: Long,
        subRunasSecundaria: String,
        ventajasAdicionales: String
    )

    @Update
    suspend fun actualizarRunas(runas: Runas)

    @Query("SELECT * FROM runas WHERE id_usuario = :idUsuario")
    suspend fun obtenerRunasPorUsuario(idUsuario: Long): List<Runas>
}