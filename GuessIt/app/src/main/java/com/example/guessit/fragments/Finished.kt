package com.example.guessit.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.guessit.databinding.FragmentFinishedBinding

class Finished : Fragment() {

    private lateinit var binding: FragmentFinishedBinding
    private val args: FinishedArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val score = args.Score
        binding.score.text = score.toString()


        binding.btnPlayAgain.setOnClickListener {
            val action = FinishedDirections.actionFinishedToTitle2()
            findNavController().navigate(action)
        }
    }

}