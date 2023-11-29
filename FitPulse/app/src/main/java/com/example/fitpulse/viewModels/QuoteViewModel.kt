package com.example.fitpulse.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitpulse.network.QuoteApiService
import com.example.fitpulse.quote.Quote
import com.example.fitpulse.quote.QuoteItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuoteViewModel : ViewModel() {

    private val _quoteInfo: MutableLiveData<Quote> = MutableLiveData()
    val quoteInfo: LiveData<Quote>
        get() = _quoteInfo


    fun getQuote(){
        val quote = QuoteApiService.getQuote()

        quote.enqueue(object : Callback<Quote?> {
            override fun onResponse(call: Call<Quote?>, response: Response<Quote?>) {
                val quoteResponse = response.body()
                _quoteInfo.postValue(quoteResponse)
                quoteResponse?.get(0)?.let { Log.d("testing", it.quote) }
            }

            override fun onFailure(call: Call<Quote?>, t: Throwable) {
                Log.d("testing", t.toString())
            }
        })
    }
}