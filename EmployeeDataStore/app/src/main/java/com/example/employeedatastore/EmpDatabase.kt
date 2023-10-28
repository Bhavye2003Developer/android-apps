package com.example.employeedatastore

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.employeedatastore.employeeRoom.EmpDao
import com.example.employeedatastore.employeeRoom.Employee
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Employee::class], version = 1, exportSchema = false)
abstract class EmpDatabase : RoomDatabase() {
    abstract fun EmpDao(): EmpDao

    companion object {
        @Volatile
        private var INSTANCE: EmpDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context): EmpDatabase {
            if (INSTANCE == null) {
                // so that no two threads create the db instance
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        EmpDatabase::class.java,
                        "EmployeeDB"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}