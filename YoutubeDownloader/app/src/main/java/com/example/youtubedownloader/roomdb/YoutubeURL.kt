package com.example.youtubedownloader.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "youtube_videos_table")
data class YoutubeURL(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val videoTitle: String,
    val downloadDate: Long = Date().time
)