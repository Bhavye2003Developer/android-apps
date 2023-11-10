package com.example.sleepqualitytracker.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SleepDatabaseDao {
    @Query("SELECT * FROM sleep_quality_table ORDER BY endTimeMillis DESC")
    fun getAllNights() : LiveData<List<SleepEntity>>

    @Insert
    suspend fun insertNewNight(night:SleepEntity)

    @Query("UPDATE sleep_quality_table SET quality=:rating WHERE id = :nightId")
    suspend fun updateNight(nightId: Long, rating: Int)

    @Query("DELETE FROM sleep_quality_table")
    suspend fun clearAllNightData()
}