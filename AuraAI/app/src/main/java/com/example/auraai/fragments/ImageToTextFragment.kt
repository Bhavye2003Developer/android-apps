package com.example.auraai.fragments

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.auraai.databinding.FragmentImageToTextBinding
import com.example.auraai.viewModels.ImageToTextViewModel
import com.example.auraai.viewModels.ImageToTextViewModelFactory

@Suppress("DEPRECATION")
class ImageToTextFragment : Fragment() {
    private lateinit var binding: FragmentImageToTextBinding
    private val args: ImageToTextFragmentArgs by navArgs()
    private lateinit var viewModel: ImageToTextViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentImageToTextBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(
            this,
            ImageToTextViewModelFactory(requireActivity().application)
        )[ImageToTextViewModel::class.java]
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageUri = Uri.parse(args.savedImageUri)
        val bitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, imageUri)
        binding.resultantImage.setImageBitmap(bitmap)

        viewModel.analyseImage(imageUri)

        viewModel.result.observe(viewLifecycleOwner) { result ->
            if (result != "") {
                binding.recognisedText.text = result
            } else {
                binding.recognisedText.text = "No text found"
            }
        }
    }
}