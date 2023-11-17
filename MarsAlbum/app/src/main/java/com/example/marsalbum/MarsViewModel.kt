package com.example.marsalbum

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Response

class MarsViewModel : ViewModel() {

    private val _response: MutableLiveData<MarsProperty> = MutableLiveData()
    val response: LiveData<MarsProperty>
        get() = _response
    fun getData(){
        val data = MarsApiService.service.getData()
        data.enqueue(object : retrofit2.Callback<MarsProperty> {
            override fun onResponse(call: Call<MarsProperty>, response: Response<MarsProperty>) {
                _response.value = response.body()
            }
            override fun onFailure(call: Call<MarsProperty>, t: Throwable) {
                t.message?.let { Log.d("testing", it) }
            }
        })
    }
}