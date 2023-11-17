package com.example.marsalbum

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://mars.udacity.com/"
interface MarsService {
    @GET("realestate")
    fun getData() : Call<MarsProperty>
}
object MarsApiService{
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service: MarsService = retrofit.create(MarsService::class.java)
}