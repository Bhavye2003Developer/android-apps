package com.example.fitpulse.room.dailyTasks

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DailyTaskDao {
    @Query("SELECT * FROM DailyTask WHERE dateTime=:todayDate AND isPermanent=0 ORDER BY dateTime DESC")
    fun getAllTodayTasks(todayDate: Long): LiveData<List<DailyTask>>

    @Query("SELECT * FROM dailytask WHERE isPermanent=1 ORDER BY dateTime DESC")
    fun getAllPermanentTasks(): LiveData<List<DailyTask>>

    @Query("UPDATE dailytask SET isTaskDone=1 WHERE id=:id")
    suspend fun updateTaskToDone(id: Int)

    @Query("UPDATE dailytask SET isPermanent=1 WHERE id=:id")
    suspend fun updateTaskToLongTerm(id: Int)

    @Insert
    suspend fun insertNewTask(dailyTask: DailyTask)

    @Delete
    suspend fun deleteTask(dailyTask: DailyTask)

    @Query("DELETE FROM DailyTask")
    suspend fun deleteAllTasks()
}