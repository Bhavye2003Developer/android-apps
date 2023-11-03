package com.example.androidtrivia.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.androidtrivia.databinding.FragmentGameWonBinding

class GameWon : Fragment() {

    private lateinit var binding: FragmentGameWonBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameWonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextMatchButton.setOnClickListener {
            val action = GameWonDirections.actionGameWonToHome2()
            findNavController().navigate(action)
        }
    }
}