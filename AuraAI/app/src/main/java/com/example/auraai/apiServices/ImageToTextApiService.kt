package com.example.auraai.apiServices

import com.example.auraai.objectDetection.ObjDetect
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ImageToTextService {
    @Headers("Authorization: Bearer ${BaseApi.API_KEY}")
    @POST("facebook/detr-resnet-50")
    fun getObjectsFromImage(@Body imageBody: RequestBody): Call<ObjDetect>
}