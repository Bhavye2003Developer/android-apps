package com.example.cipherchat

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.cipherchat.databinding.ActivityMainBinding
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val database = Firebase.database
    private val myRef = database.getReference("global_chat/messages")
    private lateinit var binding: ActivityMainBinding

    private val TAG = "cipher"
    private var allMessages = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myRef.addChildEventListener(childEventListener)

        binding.btnSendMessage.setOnClickListener {
            val newMessageText = binding.newMessage.editableText.toString()
            val message = Message(newMessageText, "Bhavye")
            MainScope().launch {
                writeNewMessageToDatabase(message)
            }
        }
    }

    private fun writeNewMessageToDatabase(message: Message) {
        myRef.push().setValue(message)
    }

    private val childEventListener = object : ChildEventListener {
        override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
            Log.d(TAG, "onChildAdded:" + dataSnapshot.key!!)

            val newMessage = dataSnapshot.getValue<Message>()
            allMessages+=newMessage?.text + "\n"

            binding.messages.text = allMessages
        }

        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            Log.d(TAG, "")
        }

        override fun onChildRemoved(snapshot: DataSnapshot) {
            Log.d(TAG, "")
        }

        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            Log.d(TAG, "")
        }

        override fun onCancelled(error: DatabaseError) {
            Log.d(TAG, "")
        }
    }
}