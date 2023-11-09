package com.example.sleepqualitytracker

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sleepqualitytracker.room.SleepDatabase
import com.example.sleepqualitytracker.room.SleepDatabaseDao
import com.example.sleepqualitytracker.room.SleepEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

class SleepViewModel(context: Context) : ViewModel() {
    private var database: SleepDatabase
    private var dao: SleepDatabaseDao
    var allNights : LiveData<List<SleepEntity>>

    init {
        database = SleepDatabase.getDatabase(context)
        dao = database.sleepDatabaseDao()
        allNights = dao.getAllNights()
    }
    fun getCurrentDateTimeInfo(): Date {
        return Date()
    }
    fun insertNewNight(night: SleepEntity){
        CoroutineScope(Dispatchers.IO).launch {
            dao.insertNewNight(night)
        }
    }

    fun createNewSleep() : SleepEntity{
        return SleepEntity()
    }
}

class SleepViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SleepViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SleepViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
