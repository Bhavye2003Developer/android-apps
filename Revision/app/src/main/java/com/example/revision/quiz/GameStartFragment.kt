package com.example.revision.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.revision.R
import com.example.revision.databinding.GameStartFragmentBinding

class GameStartFragment : Fragment(R.layout.game_start_fragment) {
    private lateinit var binding: GameStartFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.game_start_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPlay.setOnClickListener {
            // start the game.
            val action = GameStartFragmentDirections.actionGameStartFragmentToQuestionFragment()
            findNavController().navigate(action)
        }
    }

}