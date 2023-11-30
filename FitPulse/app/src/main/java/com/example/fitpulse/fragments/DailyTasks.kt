package com.example.fitpulse.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitpulse.databinding.FragmentDailyTasksBinding

class DailyTasks : Fragment() {
    private lateinit var binding: FragmentDailyTasksBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDailyTasksBinding.inflate(inflater, container, false)
        return binding.root
    }
}