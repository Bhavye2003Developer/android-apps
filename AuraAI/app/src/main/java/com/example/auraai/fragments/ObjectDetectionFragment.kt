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
import com.example.auraai.databinding.FragmentObjectDetectionBinding
import com.example.auraai.viewModels.ObjectDetectionViewModel
import com.example.auraai.viewModels.ObjectDetectionViewModelFactory

@Suppress("DEPRECATION")
class ObjectDetectionFragment : Fragment() {

    private lateinit var binding: FragmentObjectDetectionBinding
    private val args: ObjectDetectionFragmentArgs by navArgs()
    private lateinit var viewModel: ObjectDetectionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentObjectDetectionBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(
            this,
            ObjectDetectionViewModelFactory(requireActivity().application)
        )[ObjectDetectionViewModel::class.java]

        return binding.root
    }

    @SuppressLint("NewApi", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageUri = Uri.parse(args.savedImageUri)
        val bitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, imageUri)
        binding.resultantImage.setImageBitmap(bitmap)

        viewModel.analyseImage(imageUri)

        viewModel.result.observe(viewLifecycleOwner) {
            if (it == "No Object found") binding.detectedObjectsText.text = it
            else {
                binding.detectedObjectsText.text = "Objects Detected\n\n${it}"
//                val coordinateSet = viewModel.coordinates.value
            }
        }
    }
}