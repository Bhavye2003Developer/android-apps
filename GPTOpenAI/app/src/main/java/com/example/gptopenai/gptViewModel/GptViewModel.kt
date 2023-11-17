package com.example.gptopenai.gptViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gptopenai.network.ChatService
import com.google.gson.JsonParser
import kotlinx.coroutines.launch

class GptViewModel : ViewModel() {

    private val jsonParser = JsonParser()

    private val _listChats: MutableLiveData<MutableList<Chat>> = MutableLiveData(mutableListOf())
    val listChats: LiveData<MutableList<Chat>> = _listChats

    fun submitUserQuery(query: String) {

        val list = _listChats.value

        viewModelScope.launch {
            try
            {
                val chatService = ChatService(query)
                val response = chatService.getAnswer()

                val answer =
                    jsonParser.parse(response).asJsonObject.getAsJsonArray("choices")[0].asJsonObject.getAsJsonObject(
                        "message"
                    ).get("content").asString
                list?.add(Chat(query, answer))
                _listChats.postValue(list)
            }
            catch (e: java.lang.Exception){
                Log.d("ChatError", e.toString())
                list?.add(Chat(query, "Error : Please try again"))
                _listChats.postValue(list)
            }
        }
    }
}