package com.example.androidtrivia

data class Question(
    val questionText: String,
    val options: ArrayList<String>,
    val answer: String
)
