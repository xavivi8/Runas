package com.example.runas.DBControler

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface UsuarioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarUsuario(usuarios: Usuario)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUsuario(usuario: Usuario): Long

    @Query("SELECT * FROM users WHERE id = :idUsuario")
    suspend fun getUserById(idUsuario: Long): Usuario?

    @Query("SELECT id FROM users WHERE email = :usuario AND pass = :contrasenya;")
    suspend fun checkUsuario(usuario: String, contrasenya: String): Long?

    @Query("UPDATE users SET email = :nuevoNombre WHERE id = :idUsuario")
    suspend fun updateUserName(idUsuario: Long, nuevoNombre: String): Int

    @Query("UPDATE users SET pass = :nuevaContrasenya WHERE id = :idUsuario")
    suspend fun updateUserPassword(idUsuario: Long, nuevaContrasenya: String): Int

    @Query("UPDATE users SET image = :nuevaImagen WHERE id = :idUsuario")
    suspend fun updateUserImage(idUsuario: Long, nuevaImagen: ByteArray): Int



    // Método para insertar un nuevo usuario y devolver true si la inserción fue exitosa, false en caso contrario
    @Transaction
    fun insertUsuarioSafe(usuario: Usuario): Boolean {
        return try {
            insertUsuario(usuario)
            true // La inserción fue exitosa
        } catch (e: Exception) {
            false // La inserción falló
        }
    }

}