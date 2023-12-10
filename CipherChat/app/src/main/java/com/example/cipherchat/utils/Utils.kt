package com.example.cipherchat.utils

import android.annotation.SuppressLint
import com.example.cipherchat.CipherChatApp
import java.text.Format
import java.text.SimpleDateFormat
import java.util.Date

class Utils {
    //    private var database: FirebaseDatabase = Firebase.database
    private val database = CipherChatApp.database
    val globalMessagesRef = database.getReference("global_chat/messages")
    val allUsersRef = database.getReference("users")

    fun writeNewMessageToDatabase(message: Message) {
        globalMessagesRef.push().setValue(message)
    }

    fun addNewUser(username: String) {
        allUsersRef.push().setValue(username)
    }

    @SuppressLint("SimpleDateFormat")
    fun convertTime(time: Long): String? {
        val date = Date(time)
        val format: Format = SimpleDateFormat("hh:mm a")
        return format.format(date)
    }
}