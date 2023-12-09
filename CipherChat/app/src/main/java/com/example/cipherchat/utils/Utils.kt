package com.example.cipherchat.utils

import com.example.cipherchat.CipherChatApp

class Utils {
//    private var database: FirebaseDatabase = Firebase.database
    private val database = CipherChatApp.database
    val globalMessagesRef = database.getReference("global_chat/messages")

    fun writeNewMessageToDatabase(message: Message) {
        globalMessagesRef.push().setValue(message)
    }
}