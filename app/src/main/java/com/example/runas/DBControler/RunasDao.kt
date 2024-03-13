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

    @Update
    suspend fun actualizarRunas(runas: Runas)

    @Query("UPDATE runas SET nombre = :nombre, runaprincipal = :runaPrincipal, subRunasPrincipal = :subRunasPrincipal, runasecundaria = :runaSecundaria, subRunasSevundaria = :subRunasSecundaria, ventajasAdicionales = :ventajasAdicionales WHERE id_runa = :idRuna")
    suspend fun actualizarRunaPorId(
        idRuna: Long,
        nombre: String,
        runaPrincipal: String,
        subRunasPrincipal: String,
        runaSecundaria: String,
        subRunasSecundaria: String,
        ventajasAdicionales: String
    ): Int

    @Query("SELECT * FROM runas WHERE id_usuario = :idUsuario")
    suspend fun obtenerRunasPorUsuario(idUsuario: Long): List<Runas>

    @Query("SELECT * FROM runas WHERE id_runa = :idRuna")
    suspend fun obtenerRunaPorId(idRuna: Long): Runas?
}