package com.example.auraai.apiServices

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


private const val BASE_URL = "https://api-inference.huggingface.co/models/"

interface ObjectDetectionService {
    @Headers("Authorization: Bearer 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")
    @POST("facebook/detr-resnet-50")
    fun getObjectsFromImage(@Body imageBody: RequestBody) : retrofit2.Call<String>
}

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(ScalarsConverterFactory.create())
    .build();

object ObjectDetectionApiService{
    private val objectDetectionService: ObjectDetectionService = retrofit.create(ObjectDetectionService::class.java)
    fun analyseImage(@Body imageBody: RequestBody): Call<String> {
        return objectDetectionService.getObjectsFromImage(imageBody)
    }
}