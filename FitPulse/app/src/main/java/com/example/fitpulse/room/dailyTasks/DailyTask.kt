package com.example.fitpulse.room.dailyTasks

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DailyTask(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var taskText: String,
    var dateTime: Long,
    var isPermanent: Boolean = false, // if not permanent then it is a daily task.
    val isTaskDone: Boolean = false // by default task is not done yet.

)