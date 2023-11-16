package com.example.cognia.fragments

import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.cognia.ImageTextAnalyser
import com.example.cognia.databinding.FragmentImageRecognitionBinding
import com.google.mlkit.vision.text.Text

@Suppress("DEPRECATION")
class ImageRecognition : Fragment() {
    private lateinit var viewBinding: FragmentImageRecognitionBinding
    private val args: ImageRecognitionArgs by navArgs()
    private lateinit var imageTextAnalyser: ImageTextAnalyser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewBinding = FragmentImageRecognitionBinding.inflate(inflater, container, false)
        return viewBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageUri = Uri.parse(args.imageUriString)

        val bitmap = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, imageUri)
        viewBinding.imageResult.setImageBitmap(bitmap)


        imageTextAnalyser = ImageTextAnalyser()
        context?.let { imageTextAnalyser.analyze(it.applicationContext, imageUri, ::onSuccess, ::onFailure) }
    }
    private fun onSuccess(text: Text){
//        viewBinding.uriText.text = text.text
        processTextRecognitionResult(text)
    }
    private fun onFailure(e: Exception){
        Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show()
    }
    private fun processTextRecognitionResult(texts: Text) {
        val blocks = texts.textBlocks
        if (blocks.size == 0) {
            Toast.makeText(context, "No text found!", Toast.LENGTH_SHORT).show()
            // no text found
            return
        }
        var result = ""
        val resultText = texts.text
        for (block in texts.textBlocks) {
            val blockText = block.text
//            val blockCornerPoints = block.cornerPoints
//            val blockFrame = block.boundingBox
//            for (line in block.lines) {
//                val lineText = line.text
//                val lineCornerPoints = line.cornerPoints
//                val lineFrame = line.boundingBox
//                for (element in line.elements) {
//                    val elementText = element.text
//                    val elementCornerPoints = element.cornerPoints
//                    val elementFrame = element.boundingBox
//                }
//            }
            result+=blockText+"\n\n"
        }
        Toast.makeText(context, "Text recognised completely", Toast.LENGTH_SHORT).show()
        viewBinding.recognisedText.text = result
    }
}


