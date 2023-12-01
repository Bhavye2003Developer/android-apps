package com.example.fitpulse.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fitpulse.room.AppDatabase
import com.example.fitpulse.room.dailyTasks.DailyTask
import com.example.fitpulse.room.dailyTasks.DailyTaskDao
import kotlinx.coroutines.launch

class DailyTaskViewModel(application: Application) : AndroidViewModel(application) {

    private var appDatabase: AppDatabase
    private var dailyTaskDao: DailyTaskDao

    init {
        appDatabase = AppDatabase.getDatabase(application)
        dailyTaskDao = appDatabase.dailyTaskDao()
    }

    fun insertNewTask(dailyTask: DailyTask) {
        viewModelScope.launch {
            dailyTaskDao.insertNewTask(dailyTask)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class DailyTaskViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DailyTaskViewModel::class.java)) {
            return DailyTaskViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}