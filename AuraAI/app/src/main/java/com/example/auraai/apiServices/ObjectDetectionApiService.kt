package com.example.auraai.apiServices

import com.example.auraai.objectDetection.ObjDetect
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


private const val BASE_URL = "https://api-inference.huggingface.co/models/"
private const val API_KEY = "YOUR_API_KEY"

// login to https://api-inference.huggingface.co/

//used 'DETR (End-to-End Object Detection) model with ResNet-50 backbone'
interface ObjectDetectionService {
    @Headers("Authorization: Bearer $API_KEY")
    @POST("facebook/detr-resnet-50")
    fun getObjectsFromImage(@Body imageBody: RequestBody): Call<ObjDetect>
}

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object ObjectDetectionApiService {
    private val objectDetectionService: ObjectDetectionService =
        retrofit.create(ObjectDetectionService::class.java)

    fun analyseImage(@Body imageBody: RequestBody): Call<ObjDetect> {
        return objectDetectionService.getObjectsFromImage(imageBody)
    }
}