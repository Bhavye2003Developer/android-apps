package com.example.fitpulse.fragments

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fitpulse.databinding.FragmentDailyTasksBinding
import com.example.fitpulse.room.dailyTasks.DailyTask
import com.example.fitpulse.viewModels.DailyTaskViewModel
import com.example.fitpulse.viewModels.DailyTaskViewModelFactory
import java.util.Calendar
import java.util.Date

class DailyTasks : Fragment(), TaskDialogListener {
    private lateinit var viewModel: DailyTaskViewModel
    private lateinit var binding: FragmentDailyTasksBinding
    private lateinit var context: Context
    private lateinit var application: Application
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDailyTasksBinding.inflate(inflater, container, false)
        context = requireActivity()
        application = requireActivity().application
        viewModel = ViewModelProvider(
            this,
            DailyTaskViewModelFactory(application)
        )[DailyTaskViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonAddTask.setOnClickListener {
            showTaskDialog()
        }
    }

    private fun showTaskDialog() {
        val dialog = TaskDialogFragment(this)
        dialog.show(parentFragmentManager, "NEW_TASK_DIALOG")
    }

    override fun taskData(taskText: String, isPermanent: Boolean) {
        val currentDateTime: Date = Calendar.getInstance().time
        val dailyTask = DailyTask(
            taskText = taskText,
            isPermanent = isPermanent,
            dateTime = currentDateTime.time
        )
        viewModel.insertNewTask(dailyTask)
    }
}