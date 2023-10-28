package com.example.employeedatastore.employeeRoom

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class EmpRepository(private val empDao: EmpDao) {

    val allEmployees: LiveData<List<Employee>> = empDao.getAllEmployees()

    @WorkerThread
    suspend fun insert(employee: Employee) {
        insert(employee)
    }
}