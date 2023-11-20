package com.example.auraai

import android.content.Context
import android.net.Uri
import android.util.Log
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import java.io.IOException

class ImageTextAnalyser() {
    private val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
    fun analyze(
        context: Context, uri: Uri,
        onSuccess: (text: Text) -> Unit, onFailure: (e: Exception) -> Unit
    ) {
        val image: InputImage
        try {
            image = InputImage.fromFilePath(context, uri)
            // image to ml kit
            recognizer.process(image)
                .addOnSuccessListener { visionText ->
                    Log.d("testing", visionText.text)
                    // Task completed successfully
                    onSuccess(visionText)
                }
                .addOnFailureListener { e ->
                    // Task failed with an exception
                    onFailure(e)
                }
        } catch (e: IOException) {
            e.printStackTrace()
            Log.d("testing", e.toString())
        }
    }
}