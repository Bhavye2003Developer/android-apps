package com.example.androidtrivia.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.androidtrivia.Question
import com.example.androidtrivia.Questions
import com.example.androidtrivia.databinding.FragmentGameBinding

class Game : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private val questions: Questions = Questions()
    private var score: Int = 0
    private var countQuestions: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var nextQuestion = questions.getNextQuestion()
        setQuestionLayout(nextQuestion)
        ++countQuestions

        binding.submitButton.setOnClickListener {

            if (binding.questionRadioGroup.checkedRadioButtonId == -1) {
                Toast.makeText(
                    requireActivity().applicationContext,
                    "Please select 1 option",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                if (countQuestions > 3) {
                    if (score == 2) {
                        // won game
                        val action = GameDirections.actionGame2ToGameWon()
                        findNavController().navigate(action)
                    } else {
                        // lost game
                        val action = GameDirections.actionGame2ToGameLost()
                        findNavController().navigate(action)
                    }
                } else {
                    val selectedId = binding.questionRadioGroup.checkedRadioButtonId
                    val selectedRadioButton = view.findViewById<RadioButton>(selectedId)
                    if (selectedRadioButton.text == nextQuestion.answer) {
                        Toast.makeText(
                            requireActivity().applicationContext,
                            "CORRECT",
                            Toast.LENGTH_SHORT
                        ).show()
                        ++score
                    } else {
                        Toast.makeText(
                            requireActivity().applicationContext,
                            "INCORRECT",
                            Toast.LENGTH_SHORT
                        ).show()
                        --score
                    }
                    nextQuestion = questions.getNextQuestion()
                    setQuestionLayout(nextQuestion)
                    ++countQuestions
                }
            }
        }
    }

    private fun setQuestionLayout(nextQuestion: Question) {
        binding.questionText.text = nextQuestion.questionText
        binding.firstAnswerRadioButton.text = nextQuestion.options[0]
        binding.secondAnswerRadioButton.text = nextQuestion.options[1]
        binding.thirdAnswerRadioButton.text = nextQuestion.options[2]
        binding.fourthAnswerRadioButton.text = nextQuestion.options[3]
        binding.questionRadioGroup.clearCheck()
    }
}