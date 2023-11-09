package com.example.sleepqualitytracker.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SleepEntity::class], version = 1)
abstract class SleepDatabase : RoomDatabase() {
    abstract fun sleepDatabaseDao() : SleepDatabaseDao

    companion object{
        @Volatile
        private var INSTANCE: SleepDatabase? = null
        fun getDatabase(context: Context): SleepDatabase {
            // if INSTANCE OF Database is null, create it else use the built database
            if (INSTANCE==null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(
                        context,
                        SleepDatabase::class.java, "sleep-database"
                    ).build()
                }
            }
            return INSTANCE!!
        }

    }
}