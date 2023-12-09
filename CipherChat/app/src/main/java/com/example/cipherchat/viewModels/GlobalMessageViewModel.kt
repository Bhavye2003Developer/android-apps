package com.example.cipherchat.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cipherchat.utils.Message
import com.example.cipherchat.utils.Utils
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.getValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GlobalMessageViewModel : ViewModel() {

    private val utils = Utils()
    private var _allMessages: MutableLiveData<MutableList<Message>> = MutableLiveData(mutableListOf())
    val allMessages: LiveData<MutableList<Message>>
        get() = _allMessages
    private val TAG = "testing"

    fun initViewModel(){ // have to call to connect child listener to globalMessageRef
        utils.globalMessagesRef.addChildEventListener(childEventListener)
    }

    fun writeNewMessage(message: Message){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                utils.writeNewMessageToDatabase(message)
            }
        }
    }


    private val childEventListener = object : ChildEventListener {
        override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
            val newMessage = dataSnapshot.getValue<Message>()

            // have to optimise it for larger chats
            val messages = _allMessages.value
            if (newMessage != null) {
                messages?.add(newMessage)
            }
            _allMessages.value = messages
        }

        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            Log.d("testing", "messages init -> 2")
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