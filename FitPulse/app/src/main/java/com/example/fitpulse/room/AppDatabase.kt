package com.example.fitpulse.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fitpulse.room.dailyTasks.DailyTask
import com.example.fitpulse.room.dailyTasks.DailyTaskDao

@Database(entities = [DailyTask::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dailyTaskDao(): DailyTaskDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "fit_pulse_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}