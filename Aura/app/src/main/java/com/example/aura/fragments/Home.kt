package com.example.aura.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.aura.databinding.FragmentHomeBinding

class Home : Fragment() {

    private lateinit var viewBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.btnLabelImages.setOnClickListener {
            val action = HomeDirections.actionHome2ToImageLabelling()
            findNavController().navigate(action)
        }

        viewBinding.btnTextAnalysis.setOnClickListener {
            val action = HomeDirections.actionHome2ToAnalyseTextFragment()
            findNavController().navigate(action)
        }
    }
}