package com.example.auraai.viewModels

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.auraai.ImageTextAnalyser
import com.google.mlkit.vision.text.Text

class ImageToTextViewModel(private val application: Application) : AndroidViewModel(application) {

    private lateinit var imageTextAnalyser: ImageTextAnalyser
    private val _result: MutableLiveData<String> = MutableLiveData()
    val result: LiveData<String>
        get() = _result

    fun analyseImage(imageUri: Uri) {
        imageTextAnalyser = ImageTextAnalyser()
        application.applicationContext.let {
            imageTextAnalyser.analyze(
                it.applicationContext,
                imageUri,
                ::onSuccess,
                ::onFailure
            )
        }
    }

    private fun onSuccess(text: Text) {
        _result.value = processTextRecognitionResult(text)
    }

    private fun onFailure(e: Exception) {
//        Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show()
        _result.value = "Error occurred: $e"
    }

    private fun processTextRecognitionResult(texts: Text): String {
        val blocks = texts.textBlocks
        if (blocks.size == 0) {
            // no text found
            return ""
        }
        var result = ""
        for (block in texts.textBlocks) {
            val blockText = block.text
            result += blockText + "\n\n"
        }
//        Toast.makeText(context, "Text recognised completely", Toast.LENGTH_SHORT).show()
        return result
    }
}


@Suppress("UNCHECKED_CAST")
class ImageToTextViewModelFactory(private val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ImageToTextViewModel(application) as T
    }
}