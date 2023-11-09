package com.example.timerai

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimeViewModel : ViewModel() {
    private lateinit var timerObj: CountDownTimer

    private val _timeText: MutableLiveData<Int> = MutableLiveData()
    val timeText: LiveData<Int>
        get() = _timeText

    val isFinished: MutableLiveData<Boolean> = MutableLiveData(false)

    fun startTimer(timeCount: Int) {
        timerObj = timer(timeCount = timeCount)
        timerObj.start()
    }

    fun stopTimer() {
        timerObj.cancel()
    }

    fun resumeTimer() {
        val timeLeft = _timeText.value?.toInt()
        timerObj.cancel() // to cancel previous instance of countDownTimer
        timerObj = timer(timeLeft ?: 0)
        timerObj.start()
    }

    private fun timer(timeCount: Int = 0): CountDownTimer {
        return object : CountDownTimer(((timeCount + 1) * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _timeText.value = (millisUntilFinished / 1000).toInt()
            }

            override fun onFinish() {
                isFinished.value = true
            }
        }
    }
}