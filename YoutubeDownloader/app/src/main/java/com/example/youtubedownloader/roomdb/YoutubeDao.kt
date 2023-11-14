package com.example.youtubedownloader.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface YoutubeDao {
    @Query("SELECT * FROM youtube_videos_table ORDER BY downloadDate")
    fun getAllVideos() : LiveData<List<YoutubeURL>>

    @Insert
    suspend fun insertUrl(youtubeURL: YoutubeURL)
}