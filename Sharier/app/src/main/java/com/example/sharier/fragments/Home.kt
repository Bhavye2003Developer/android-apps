package com.example.sharier.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sharier.databinding.FragmentHomeBinding


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
            Toast.makeText(
                requireActivity().applicationContext,
                "Opening sender",
                Toast.LENGTH_SHORT
            ).show()
            val action = HomeDirections.actionHome2ToSender()
            findNavController().navigate(action)
        }

        binding.btnReceiver.setOnClickListener {
            Toast.makeText(
                requireActivity().applicationContext,
                "Opening receiver",
                Toast.LENGTH_SHORT
            ).show()
            val action = HomeDirections.actionHome2ToReceiver()
            findNavController().navigate(action)
        }
    }

}