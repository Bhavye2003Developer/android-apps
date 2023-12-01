package com.example.fitpulse.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fitpulse.room.AppDatabase
import com.example.fitpulse.room.dailyTasks.DailyTask
import com.example.fitpulse.room.dailyTasks.DailyTaskDao
import kotlinx.coroutines.launch
import java.util.Calendar

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

    fun removeTask(dailyTask: DailyTask) {
        viewModelScope.launch {
            dailyTaskDao.deleteTask(dailyTask)
        }
    }

    fun updateTaskToDone(id: Int) {
        viewModelScope.launch {
            dailyTaskDao.updateTaskToDone(id)
        }
    }

    fun updateTaskToLongTerm(id: Int) {
        viewModelScope.launch {
            dailyTaskDao.updateTaskToLongTerm(id)
        }
    }

    val allDailyTasks: LiveData<List<DailyTask>>
        get() = dailyTaskDao.getAllTodayTasks(getTodayDate())

    val allPermanentTasks: LiveData<List<DailyTask>>
        get() = dailyTaskDao.getAllPermanentTasks()

    private fun getTodayDate(): Long {
        val cal = Calendar.getInstance()
        cal[Calendar.HOUR_OF_DAY] = 0
        cal[Calendar.MINUTE] = 0
        cal[Calendar.SECOND] = 0
        cal[Calendar.MILLISECOND] = 0
        return cal.timeInMillis
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