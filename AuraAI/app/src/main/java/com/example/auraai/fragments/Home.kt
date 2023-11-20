package com.example.auraai.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.auraai.databinding.FragmentHomeBinding

class Home : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnObjectDetection.setOnClickListener {
            val action = HomeDirections.actionHome2ToImageCapture(1)
            findNavController().navigate(action)
        }

        binding.btnImageToText.setOnClickListener {
            val action = HomeDirections.actionHome2ToImageCapture(2)
            findNavController().navigate(action)
        }
    }
}