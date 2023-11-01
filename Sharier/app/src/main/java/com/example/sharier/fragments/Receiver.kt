package com.example.sharier.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sharier.databinding.FragmentReceiverBinding

class Receiver : Fragment() {

    private lateinit var binding: FragmentReceiverBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentReceiverBinding.inflate(inflater, container, false)
        return binding.root
    }

}