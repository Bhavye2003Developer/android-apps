package com.example.nasafetcher.network.satSearch

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


const val BASE_URL = "https://tle.ivanstanojevic.me/api/tle/"
//https://tle.ivanstanojevic.me/api/tle/48353/propagate -> example api

interface Api {
    @GET("{id}/propagate")
    suspend fun getData(@Path("id") id: String): Satellite
}

var retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object SatService {

    private val service: Api = retrofit.create(Api::class.java)
    suspend fun getSatData(id: String): Satellite {
        return service.getData(id)
    }
}