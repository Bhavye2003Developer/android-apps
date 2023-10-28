package com.example.employeedatastore

import android.app.Application
import com.example.employeedatastore.employeeRoom.EmpRepository

class BaseApplication : Application() {

    private val database by lazy { EmpDatabase.getDatabase(applicationContext) }
    val repository by lazy { EmpRepository(database.EmpDao()) }

}