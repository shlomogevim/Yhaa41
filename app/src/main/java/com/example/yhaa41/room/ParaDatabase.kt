package com.example.yhaa41.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = arrayOf(Para::class),
    version = 1
)
abstract class ParaDatabase:RoomDatabase() {
    abstract fun getParaDao(): NewParaDao
    companion object {
        @Volatile private var instance: ParaDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ParaDatabase::class.java,
            "paradatabase2"
        ).build()
    }

}
