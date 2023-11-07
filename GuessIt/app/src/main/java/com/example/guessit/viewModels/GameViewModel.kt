package com.example.guessit.viewModels

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guessit.Words

class GameViewModel : ViewModel(){

    // timer
    private val timer = object: CountDownTimer(61000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            _timeCount.value = DateUtils.formatElapsedTime(millisUntilFinished/1000)
        }
        override fun onFinish() {
            _currentWord.value = ""
        }
    }

    private val _currentWord = MutableLiveData<String>()
    val currentWord : LiveData<String>
        get() = _currentWord

    private val _currentScore = MutableLiveData<Int>()
    val currentScore: LiveData<Int>
        get() = _currentScore

    private val _timeCount = MutableLiveData<String>()
    val timeCount: LiveData<String>
        get() = _timeCount


    val isBuzz: MutableLiveData<Boolean> = MutableLiveData(false)


    private var words: Words = Words()
    fun getNewWord(){
        _currentWord.value = words.getWord()
    }
    init {
        getNewWord()
        _currentScore.value = 0
        timer.start()
    }
    fun incrementScore(){
        _currentScore.value = currentScore.value!! + 1
    }
    fun decrementScore(){
        _currentScore.value = currentScore.value!! - 1
    }
}