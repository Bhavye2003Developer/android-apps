package com.example.nasafetcher.network.astronomyPicDay

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://api.nasa.gov/planetary/"
private const val API_KEY = "siHNUNEpephrTVKdlgfdyPxPxZXnmO30xcZog0tH"

//https://api.nasa.gov/planetary/apod?api_key=siHNUNEpephrTVKdlgfdyPxPxZXnmO30xcZog0tH

interface Service {
    @GET("apod?api_key=$API_KEY")
    suspend fun getJson(): JsonFormat
}

private val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()


object Api {
    private var service: Service = retrofit.create(Service::class.java)

    suspend fun getJsonData(): JsonFormat {
        return service.getJson()
    }
}