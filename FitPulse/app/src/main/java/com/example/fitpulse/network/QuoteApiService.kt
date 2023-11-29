package com.example.fitpulse.network

import com.example.fitpulse.quote.Quote
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers


private const val BASE_URL = "https://api.api-ninjas.com/v1/"
private const val API_KEY = "O06mkRQNPgQYSN6v4JMrsw==WKEIuoroxsutxaOM"

private interface QuoteService{
    @Headers("X-Api-Key: $API_KEY")
    @GET("quotes?category=fitness")
    fun getQuote() : Call<Quote> // executes on background thread by default.
}

var retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object QuoteApiService {
    private val service = retrofit.create(QuoteService::class.java)
    fun getQuote() : Call<Quote>{
        return service.getQuote()
    }
}