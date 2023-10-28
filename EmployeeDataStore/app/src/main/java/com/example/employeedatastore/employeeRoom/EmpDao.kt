package com.example.employeedatastore.employeeRoom

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EmpDao {

    @Query("SELECT * FROM Employees_Table ORDER BY `Employee Name` ASC")
    fun getAllEmployees() : LiveData<List<Employee>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEmployee(employee: Employee)
}