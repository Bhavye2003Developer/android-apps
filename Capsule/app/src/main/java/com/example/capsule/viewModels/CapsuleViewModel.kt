package com.example.capsule.viewModels

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CapsuleViewModel : ViewModel() {
    private val _milliSecondsLeft: MutableLiveData<String> = MutableLiveData()
    val milliSecondsLeft: LiveData<String>
        get() = _milliSecondsLeft

    private val timer = getTimer()
    fun startTimer() {
        // starting timer on background thread
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                timer.start()
            }
        }
    }

    private fun getTimer(): CountDownTimer {
        val seconds = 10 * 60 // 10 minutes
        val timer = object : CountDownTimer((seconds * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsTime = (millisUntilFinished / 1000).toInt()
                _milliSecondsLeft.value = "${DateUtils.formatElapsedTime(secondsTime.toLong())} min"
            }

            override fun onFinish() {}
        }
        return timer
    }

}