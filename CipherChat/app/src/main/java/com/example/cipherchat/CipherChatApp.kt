package com.example.cipherchat

import android.app.Application
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CipherChatApp: Application() {
    override fun onCreate() {
        super.onCreate()
        database = Firebase.database
    }
    companion object {
        lateinit var database: FirebaseDatabase
    }
}