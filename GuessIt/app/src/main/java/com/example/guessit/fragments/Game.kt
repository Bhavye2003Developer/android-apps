package com.example.guessit.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.guessit.databinding.FragmentGameBinding
import com.example.guessit.viewModels.GameViewModel

@Suppress("DEPRECATION")
class Game : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private lateinit var viewModel: GameViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentGameBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[GameViewModel::class.java]

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnGotIt.setOnClickListener {
            viewModel.incrementScore()
            viewModel.getNewWord()
            viewModel.isBuzz.value = true
        }

        binding.btnSkip.setOnClickListener {
            viewModel.decrementScore()
            viewModel.getNewWord()
        }

        viewModel.currentScore.observe(viewLifecycleOwner) {
            binding.currentScore.text = "Current Score: ${viewModel.currentScore.value!!}"
        }

        viewModel.currentWord.observe(viewLifecycleOwner) {
            if (it != "") {
                binding.word.text = "\"" + it + "\""
            } else {
                // Game finished
                gameFinished()
            }
        }

        viewModel.timeCount.observe(viewLifecycleOwner){
            binding.timer.text = it
        }

        viewModel.isBuzz.observe(viewLifecycleOwner){
            if (it) {
                vibratePhone()
                viewModel.isBuzz.value = false
            }
        }
    }

    private fun gameFinished(){
        val currentScore = viewModel.currentScore.value!!
        val action = GameDirections.actionGameToFinished(currentScore)
        findNavController().navigate(action)
    }


    private fun Fragment.vibratePhone() {
        val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(200)
        }
    }
}