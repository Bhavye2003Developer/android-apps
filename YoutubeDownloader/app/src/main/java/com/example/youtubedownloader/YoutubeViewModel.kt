package com.example.youtubedownloader

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.youtubedownloader.roomdb.YoutubeDatabase
import com.example.youtubedownloader.roomdb.YoutubeURL
import kotlinx.coroutines.launch

class YoutubeViewModel(application: Application) : AndroidViewModel(application) {
    // url processing
    private var database: YoutubeDatabase

    init {
        database = YoutubeDatabase.getInstance(application)
    }
    fun insertUrl(youtubeURL: YoutubeURL){
        viewModelScope.launch {
            database.youtubeDao().insertUrl(youtubeURL)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class YoutubeViewModelFactory(private val application: Application) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(YoutubeViewModel::class.java)) {
            YoutubeViewModel(application) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}