package com.example.nasafetcher.network.spaceNews

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://api.spaceflightnewsapi.net/v3/"

interface Service {
    @GET("articles")
    suspend fun getArticles(): ArrayList<News>
}

private val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()


object Api {
    private var service: Service = retrofit.create(Service::class.java)

    suspend fun getData(): ArrayList<News> {
        return service.getArticles()
    }
}