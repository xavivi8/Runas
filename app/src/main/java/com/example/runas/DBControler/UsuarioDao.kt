package com.example.runas.DBControler

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UsuarioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarUsuario(usuarios: Usuario)

    @Query("SELECT id FROM usuarios WHERE usuario = :usuario AND contrasenya = :contrasenya;")
    suspend fun checkUsuario(usuario: String, contrasenya: String): Long?
}