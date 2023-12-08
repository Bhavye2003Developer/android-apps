package com.example.capsule.fragments

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.capsule.R
import com.example.capsule.databinding.FragmentQuizBinding
import com.example.capsule.utility.Question
import com.example.capsule.viewModels.QuizViewModel

class QuizFragment : Fragment() {

    private lateinit var binding: FragmentQuizBinding
    private lateinit var quizViewModel: QuizViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuizBinding.inflate(inflater, container, false)
        quizViewModel = ViewModelProvider(this)[QuizViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()

        binding.checkAnswerBtn.setOnClickListener {
            val selectedId = binding.answerOptionsGroup.checkedRadioButtonId

            if (selectedId == -1) {
                // no option selected
                Toast.makeText(requireContext(), "No option selected", Toast.LENGTH_SHORT).show()
            } else {
                val selectedOption = view.findViewById<RadioButton>(selectedId).text.toString()
                if (quizViewModel.checkAnswer(selectedOption)) {
                    Toast.makeText(requireContext(), "Correct", Toast.LENGTH_SHORT).show()
                    quizViewModel.incrementScore()
                } else {
                    Toast.makeText(requireContext(), "Incorrect!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.nextQuestionBtn.setOnClickListener {
            val question = quizViewModel.getQuestion()
            if (question != null) {
                setNewQuestionUI(question)
                updateProgressBar(quizViewModel.getCurrentProgress())
            } else {
                navigateToResult(quizViewModel.getTotalScore() * 10)
            }
        }
    }

    private fun initUI() {
        val question = quizViewModel.getQuestion()
        if (question != null) {
            setNewQuestionUI(question)
            updateProgressBar(quizViewModel.getCurrentProgress())
        }
    }


    private fun setNewQuestionUI(question: Question) {
        binding.apply {
            answerOptionsGroup.clearCheck()
            questionTextView.text = question.questionText
            option1RadioButton.text = question.options[0]
            option2RadioButton.text = question.options[1]
            option3RadioButton.text = question.options[2]
            option4RadioButton.text = question.options[3]
        }
    }

    private fun updateProgressBar(progressValue: Int) {
        ObjectAnimator.ofInt(binding.progressBar, "progress", progressValue)
            .setDuration(300)
            .start()
    }


    @SuppressLint("InflateParams", "SetTextI18n")
    private fun navigateToResult(totalScore: Int) {
        binding.fragmentQuizConstraintLayout.visibility = View.GONE
        val view2 = layoutInflater.inflate(R.layout.fragment_quiz_result, null)
        val displayedScore = view2.findViewById<TextView>(R.id.displayedScoreTextView)
        displayedScore.text = "$totalScore%"
        binding.fragmentQuizLayout.addView(view2)
    }
}