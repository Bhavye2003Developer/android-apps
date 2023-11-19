package com.example.auraai.viewModels

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.auraai.apiServices.ObjectDetectionApiService
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream

class ObjectDetectionViewModel(private val application: Application) : AndroidViewModel(application) {
    private val apiService = ObjectDetectionApiService

    private val _result: MutableLiveData<String> = MutableLiveData()
    val result: LiveData<String>
        get() = _result


    fun analyseImage(imageUri: Uri){
        val iStream: InputStream = application.contentResolver.openInputStream(imageUri)!!
        val inputData: ByteArray = getBytes(iStream)!!

        val requestBody = RequestBody.create(
            MediaType.parse("application/octet"),
            inputData)

        val call = apiService.analyseImage(
            requestBody
        )

        // It automatically executes on a background thread
        call.enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                _result.postValue(response.body())
            }
            override fun onFailure(call: Call<String?>, t: Throwable) {
                Log.d("testing", t.message.toString())
            }
        })
    }

    @Throws(IOException::class)
    fun getBytes(inputStream: InputStream): ByteArray? {
        val byteBuffer = ByteArrayOutputStream()
        val bufferSize = 1024
        val buffer = ByteArray(bufferSize)
        var len: Int
        while (inputStream.read(buffer).also { len = it } != -1) {
            byteBuffer.write(buffer, 0, len)
        }
        return byteBuffer.toByteArray()
    }
}

@Suppress("UNCHECKED_CAST")
class ObjectDetectionViewModelFactory(private val application: Application) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ObjectDetectionViewModel(application) as T
    }
}