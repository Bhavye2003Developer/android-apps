package com.example.aura.viewModels


import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.aura.analysers.Image
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabel
import java.io.IOException

class ImageViewModel(application: Application) : AndroidViewModel(application) {

    private val imageAnalyser = Image()
    var result: MutableLiveData<String> = MutableLiveData("Not processed")
    fun processImage(imageUri: Uri) {
        val image: InputImage
        try {
            image =
                InputImage.fromFilePath(getApplication<Application>().applicationContext, imageUri)
//            Log.d("photo", "Captured viewmodel")

            imageAnalyser.analyse(image, ::onSuccess, ::onFailure)

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun onSuccess(labels: List<ImageLabel>) {
        var labelsResult = ""
        for (label in labels) {
            labelsResult += "${label.text}\n"
        }
        result.value = labelsResult
    }

    private fun onFailure(err: String) {
        result.value = err
    }
}