package com.example.gptopenai.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

const val api_key = ""
const val BASE_URL = "https://api.openai.com/"


var client: OkHttpClient = OkHttpClient.Builder()
    .connectTimeout(6000, TimeUnit.SECONDS)
    .readTimeout(6000, TimeUnit.SECONDS).build()


var retrofit: Retrofit =
    Retrofit.Builder().baseUrl(BASE_URL).client(client).addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(
            GsonConverterFactory.create()
        ).build()


interface ChatApiService {
    @Headers(
        "Content-Type: application/json", "Authorization: Bearer $api_key"
    )
    @POST("v1/chat/completions")
    suspend fun getAnswer(@Body body: DataBody): String
}

data class DataBody(
    var model: String, var messages: List<Message>, var temperature: Double
)

data class Message(
    var role: String, var content: String
)


class ChatService(query: String) {
    private val body = DataBody(
        "gpt-3.5-turbo", listOf(Message("user", query)), 0.7
    )
    private val service: ChatApiService = retrofit.create(ChatApiService::class.java)

    suspend fun getAnswer(): String {
        return service.getAnswer(body)
    }
}