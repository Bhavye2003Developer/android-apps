package com.example.cipherchat.utils

data class Message(
    val text: String = "no message",
    val sender: String = "sender",
    val sendTime: Long = System.currentTimeMillis()
)