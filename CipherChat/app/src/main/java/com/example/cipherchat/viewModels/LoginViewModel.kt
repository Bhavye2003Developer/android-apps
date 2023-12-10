package com.example.cipherchat.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cipherchat.utils.Utils
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class LoginViewModel : ViewModel() {

    private val util = Utils()
    private val _isUsernameExists: MutableLiveData<Boolean?> = MutableLiveData(null)
    val isUsernameExists: LiveData<Boolean?>
        get() = _isUsernameExists

    private val _isUsernameAdded: MutableLiveData<Boolean> = MutableLiveData(false)
    val isUsernameAdded: LiveData<Boolean>
        get() = _isUsernameAdded

    fun getUserInfo(username: String) {

        util.allUsersRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val users = snapshot.children

                var isUsernameExists: Boolean = false

                // optimise for big users data
                users.forEach {
                    if (it.value == username) {
                        isUsernameExists = true
                    }
                }

                // if username doesn't exists, add it to table
                if (!isUsernameExists) {
                    Log.d("testing", "setting up")
                    setUsername(username)
                } else {
                    _isUsernameExists.value = true
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("testing", "cancelled user's query.")
            }
        })
    }

    fun setUsername(username: String) {
        util.addNewUser(username)
    }
}