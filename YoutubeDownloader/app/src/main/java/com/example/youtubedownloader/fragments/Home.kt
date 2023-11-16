package com.example.youtubedownloader.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
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
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnDownload.setOnClickListener {
            val url = binding.urlText.editableText.toString()
            Toast.makeText(context, "Processing url...", Toast.LENGTH_SHORT).show()
            // process the url
            viewModel.processURL(url)

            binding.progressBarVideoDownload.visibility = View.VISIBLE
            binding.downloadPercent.visibility = View.VISIBLE
            binding.urlText.isEnabled = false
            binding.btnDownload.isEnabled = false
            binding.downloads.isEnabled = false

            viewModel.videoDownloadProgress.observe(viewLifecycleOwner){
                if (it>0.0){
                    binding.progressBarVideoDownload.setProgress(it.toInt(), true)
                    binding.downloadPercent.text = "${String.format("%.2f", it)}%"
                }

                if (it>=100.00){
                    timer.start()
                    // humble visibility gone progressbar
                }
            }
        }
        binding.downloads.setOnClickListener {
            Toast.makeText(context, "Opening downloads page...", Toast.LENGTH_SHORT).show()
            val action = HomeDirections.actionHome2ToDownloads2()
            findNavController().navigate(action)
        }
    }

    private val timer = object: CountDownTimer(1000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
        }
        override fun onFinish() {
            binding.progressBarVideoDownload.visibility = View.GONE
            binding.downloadPercent.visibility = View.GONE
            binding.urlText.isEnabled = true
            binding.btnDownload.isEnabled = true
            binding.downloads.isEnabled = true
            binding.urlText.text?.clear()
            viewModel.finishDownload()
            Toast.makeText(context, "Download complete", Toast.LENGTH_SHORT).show()
        }
    }
}