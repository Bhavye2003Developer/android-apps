package com.example.timerai

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimeViewModel : ViewModel() {
    private lateinit var timerObj: CountDownTimer
    private val _timeText: MutableLiveData<String> = MutableLiveData()
    val timeText: LiveData<String>
        get() = _timeText

    val isFinished: MutableLiveData<Boolean> = MutableLiveData(false)

    fun startTimer(timeCount: Int) {
        timerObj = timer(timeCount = timeCount)
        timerObj.start()
    }

    fun stopTimer() {
        timerObj.cancel()
    }

    private fun timer(timeCount: Int = 0): CountDownTimer {
        return object : CountDownTimer(((timeCount + 1) * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _timeText.value = DateUtils.formatElapsedTime(millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                isFinished.value = true
            }
        }
    }
}