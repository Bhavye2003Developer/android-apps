package com.example.fitpulse.room.dailyTasks

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DailyTaskDao {
    @Query("SELECT * FROM DailyTask WHERE dateTime=:todayDate AND isPermanent=0 ORDER BY dateTime DESC")
    fun getAllTodayTasks(todayDate: Long): List<DailyTask>

    @Query("SELECT * FROM dailytask WHERE isPermanent=1 ORDER BY dateTime DESC")
    fun getAllPermanentTasks(): List<DailyTask>

    @Insert
    suspend fun insertNewTask(dailyTask: DailyTask)

    @Delete
    suspend fun deleteTask(dailyTask: DailyTask)

    @Query("DELETE FROM DailyTask")
    suspend fun deleteAllTasks()
}