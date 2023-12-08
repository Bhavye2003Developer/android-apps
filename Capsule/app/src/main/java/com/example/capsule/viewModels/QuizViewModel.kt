package com.example.capsule.viewModels

import androidx.lifecycle.ViewModel
import com.example.capsule.utility.Question
import com.example.capsule.utility.Utils

class QuizViewModel : ViewModel() {

    private val quiz = Utils.Quiz()
    private var currentQuestion: Question? = null

    fun getQuestion(): Question? {
        currentQuestion = quiz.getQuestion()
        return currentQuestion
    }

    fun checkAnswer(selectedOption: String): Boolean {
        return currentQuestion?.answer == selectedOption
    }

    fun incrementScore() {
        quiz.incrementScore()
    }

    fun getTotalScore(): Int {
        return quiz.getTotalScore()
    }

    fun getCurrentProgress(): Int {
        return quiz.questionIndex * 10
    }
}