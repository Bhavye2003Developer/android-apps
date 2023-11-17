package com.example.gptopenai

import com.google.gson.JsonParser
import kotlinx.coroutines.*
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


const val api_key = "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"
const val BASE_URL = "https://api.openai.com/"

var retrofit: Retrofit =
    Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(
            GsonConverterFactory.create()
        ).build()

interface ApiService {
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


@OptIn(DelicateCoroutinesApi::class)
fun main() = runBlocking {

    val body = DataBody("gpt-3.5-turbo", listOf(Message("user", "Who is god krishna")), 0.7)

    val jsonParser = JsonParser()

    val service = retrofit.create(ApiService::class.java)

    val posts = GlobalScope.async {
        val posts = service.getAnswer(
            body
        )
        val answer = jsonParser.parse(posts).asJsonObject.getAsJsonArray("choices")[0].asJsonObject.getAsJsonObject("message")
        println(answer.get("content"))
    }
    posts.await()

}