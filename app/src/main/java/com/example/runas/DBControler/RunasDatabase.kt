package com.example.runas.DBControler

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Runas::class], version = 2)
abstract class RunasDatabase:  RoomDatabase() {
    abstract  fun runasDao(): RunasDao

    companion object{
        @Volatile
        private  var instance: RunasDatabase? = null
        private val DBNAME = "runas.db"
        private val LOCK = Any()

        operator  fun invoke(contexto: Context) = instance ?: synchronized((LOCK)){
            instance ?: buildDatabase(contexto).also { instance = it }
        }

        private fun buildDatabase(contexto: Context) = Room.databaseBuilder(
            contexto, RunasDatabase::class.java, DBNAME
        ).fallbackToDestructiveMigration().build()
    }
}