package com.example.revision.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.revision.R
import com.example.revision.databinding.QuestionFragmentBinding

class QuestionFragment : Fragment(R.layout.question_fragment) {

    private lateinit var binding: QuestionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.question_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentQuestion = QuizActivity.questions.getQuestion()

        if (currentQuestion.questionText==""){
            // game finished
            if (QuizActivity.questions.isWon()){
                val action = QuestionFragmentDirections.actionQuestionFragmentToGameWonFragment()
                findNavController().navigate(action)
            }
            else{
                val action = QuestionFragmentDirections.actionQuestionFragmentToTryAgainFragment()
                findNavController().navigate(action)
            }
        }

        binding.question = currentQuestion

        binding.btnSubmit.setOnClickListener {
            val selectedId = binding.options.checkedRadioButtonId

            if (selectedId==-1) {
                Toast.makeText(activity, "Please select an option", Toast.LENGTH_SHORT).show()
            }
            else {
                val selectedOption = view.findViewById<RadioButton>(selectedId)
                Toast.makeText(activity, "You have selected : ${selectedOption.text}", Toast.LENGTH_SHORT).show()
                if (selectedOption.text==currentQuestion.answer){
                    // correct answer
                    QuizActivity.questions.incrementScore()
                }
                val action = QuestionFragmentDirections.actionQuestionFragmentSelf()
                findNavController().navigate(action)
            }
        }

    }
}