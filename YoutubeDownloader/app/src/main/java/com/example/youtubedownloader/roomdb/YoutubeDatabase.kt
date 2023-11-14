package com.example.youtubedownloader.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [YoutubeURL::class], version = 1, exportSchema = false)
abstract class YoutubeDatabase : RoomDatabase() {
    abstract fun youtubeDao() : YoutubeDao
    companion object{

        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: YoutubeDatabase? = null

        fun getInstance(context: Context) : YoutubeDatabase{
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    YoutubeDatabase::class.java,
                    "downloads_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

    }

}