package com.example.employeedatastore

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.employeedatastore.employeeRoom.EmpRepository
import com.example.employeedatastore.employeeRoom.Employee
import kotlinx.coroutines.launch

class EmpViewModel(private val empRepository: EmpRepository) : ViewModel() {
    val allEmployees: LiveData<List<Employee>> = empRepository.allEmployees

    fun insertEmployee(employee: Employee) {
        viewModelScope.launch {
            empRepository.insert(employee)
        }
    }
}


@Suppress("UNCHECKED_CAST")
class EmployeeViewModelFactory(private val empRepository: EmpRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        println("hey")
        if (modelClass.isAssignableFrom(EmpViewModel::class.java))
            return EmpViewModel(empRepository) as T
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}