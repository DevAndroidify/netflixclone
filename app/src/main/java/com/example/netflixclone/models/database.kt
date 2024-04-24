package com.example.netflixclone.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Define the entities that will be part of the database
@Database(entities = [downloadclass::class], version = 1, exportSchema = false)
abstract class database : RoomDatabase() {

    // Define the abstract method that returns your DAO
    abstract fun cartdao(): downloaddao

    companion object {
        @Volatile
        private var INSTANCE: database? = null

        // Get an instance of the database
        fun getDatabase(context: Context): database {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    database::class.java,
                    "downloads"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
