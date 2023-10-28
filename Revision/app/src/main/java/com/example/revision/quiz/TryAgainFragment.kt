package com.example.revision.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.revision.R
import com.example.revision.databinding.TryAgainQuizFragmentBinding

class TryAgainFragment : Fragment(R.layout.try_again_quiz_fragment) {

    private lateinit var binding: TryAgainQuizFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.try_again_quiz_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tryAgain.setOnClickListener {
            Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
        }
    }
}