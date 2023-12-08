package com.example.cipherchat

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Utils {
    private var database: FirebaseDatabase = Firebase.database
    private val globalMessagesRef = database.getReference("global_chat/messages")


    private fun writeNewMessageToDatabase(message: Message) {
        globalMessagesRef.push().setValue(message)
    }
}