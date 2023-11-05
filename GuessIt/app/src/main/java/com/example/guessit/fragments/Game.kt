package com.example.guessit.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.guessit.Words
import com.example.guessit.databinding.FragmentGameBinding

class Game : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private lateinit var words: Words
    private var currentScore: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentGameBinding.inflate(inflater, container, false)
        words = Words()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getWord(0)

        binding.btnGotIt.setOnClickListener {
            ++currentScore
            getWord(currentScore)
        }

        binding.btnSkip.setOnClickListener {
            --currentScore
            getWord(currentScore)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getWord(score: Int){
        val word = words.getWord()
        if (word!=""){
            binding.word.text = "\"" + word + "\""
        }
        else{
            // Game finished
            val action = GameDirections.actionGameToFinished(currentScore)
            findNavController().navigate(action)
        }
        showScoreText(score)
    }

    @SuppressLint("SetTextI18n")
    fun showScoreText(score: Int){
        binding.currentScore.text = "Current Score: $score"
    }
}