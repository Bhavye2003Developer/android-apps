package com.example.youtubedownloader.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.youtubedownloader.roomdb.YoutubeDatabase

class YoutubeDownloadViewModel(application: Application) : AndroidViewModel(application) {
    private var database: YoutubeDatabase

    init {
        database = YoutubeDatabase.getInstance(application)
    }


}