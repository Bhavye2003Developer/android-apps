package com.example.employeedatastore.employeeRoom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Entity -> SQLite Table
@Entity(tableName = "Employees_Table")
data class Employee(
    @PrimaryKey
    @ColumnInfo(name = "Employee ID")
    val employeeId: String,

    @ColumnInfo(name = "Employee Name")
    val name: String
)