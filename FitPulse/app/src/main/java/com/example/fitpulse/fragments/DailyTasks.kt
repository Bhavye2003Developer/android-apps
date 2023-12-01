package com.example.fitpulse.fragments

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fitpulse.adapters.TaskListAdapter
import com.example.fitpulse.databinding.FragmentDailyTasksBinding
import com.example.fitpulse.room.dailyTasks.DailyTask
import com.example.fitpulse.viewModels.DailyTaskViewModel
import com.example.fitpulse.viewModels.DailyTaskViewModelFactory
import java.util.Calendar

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

        viewModel.allDailyTasks.observe(viewLifecycleOwner) {
            binding.todayTasksRecyclerView.adapter =
                TaskListAdapter(it, ::removeTask, ::updateTaskToDone, ::updateTaskToLongTerm)
        }
    }

    private fun showTaskDialog() {
        val dialog = TaskDialogFragment(this)
        dialog.show(parentFragmentManager, "NEW_TASK_DIALOG")
    }

    override fun taskData(taskText: String, isPermanent: Boolean) {
        val currentDate = getTodayDate()
        val dailyTask = DailyTask(
            taskText = taskText,
            isPermanent = isPermanent,
            dateTime = currentDate
        )
        viewModel.insertNewTask(dailyTask)
    }


    private fun getTodayDate(): Long {
        val cal = Calendar.getInstance()
        cal[Calendar.HOUR_OF_DAY] = 0
        cal[Calendar.MINUTE] = 0
        cal[Calendar.SECOND] = 0
        cal[Calendar.MILLISECOND] = 0
        return cal.timeInMillis
    }

    private fun removeTask(dailyTask: DailyTask) {
        viewModel.removeTask(dailyTask)
    }

    private fun updateTaskToDone(id: Int) {
        viewModel.updateTaskToDone(id)
    }

    private fun updateTaskToLongTerm(id: Int) {
        viewModel.updateTaskToLongTerm(id)
    }
}