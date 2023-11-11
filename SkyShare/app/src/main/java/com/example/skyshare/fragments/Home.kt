package com.example.skyshare.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.skyshare.R
import com.example.skyshare.databinding.FragmentHomeBinding

class Home : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSender.setOnClickListener {
            val action = HomeDirections.actionHome2ToSender()
            findNavController().navigate(action)
        }

        binding.btnReceiver.setOnClickListener {
            val action = HomeDirections.actionHome2ToReceiver()
            findNavController().navigate(action)
        }
    }
}