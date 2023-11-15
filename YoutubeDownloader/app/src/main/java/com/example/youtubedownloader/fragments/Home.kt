package com.example.youtubedownloader.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.youtubedownloader.databinding.FragmentHomeBinding
import com.example.youtubedownloader.viewModels.YoutubeViewModel
import com.example.youtubedownloader.viewModels.YoutubeViewModelFactory

class Home : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: YoutubeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this,
            YoutubeViewModelFactory(application = requireActivity().application)
        )[YoutubeViewModel::class.java]

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnDownload.setOnClickListener {
            val url = binding.urlText.editableText.toString()
            Toast.makeText(context, "Processing url...", Toast.LENGTH_SHORT).show()
            // process the url
            viewModel.processURL(url)
        }
        binding.downloads.setOnClickListener {
            Toast.makeText(context, "Opening downloads page...", Toast.LENGTH_SHORT).show()
            val action = HomeDirections.actionHome2ToDownloads2()
            findNavController().navigate(action)
        }
    }


}