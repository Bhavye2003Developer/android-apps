package com.example.aura.analysers

import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabel
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions

class Image {
    fun analyse(
        image: InputImage,
        onSuccess: (labels: List<ImageLabel>) -> Unit,
        onFailure: (err: String) -> Unit
    ) {
        // To use default options:
        val labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)

        labeler.process(image)
            .addOnSuccessListener { labels ->
                // Task completed successfully
                onSuccess(labels)
            }
            .addOnFailureListener { e ->
                // Task failed with an exception
                onFailure(e.toString())
            }
    }
}