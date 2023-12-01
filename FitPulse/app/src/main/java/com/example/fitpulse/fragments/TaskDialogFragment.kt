package com.example.fitpulse.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.fitpulse.R


class TaskDialogFragment(private val taskDialogListener: TaskDialogListener) : DialogFragment() {

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater.
            val inflater = requireActivity().layoutInflater
            val dialogView: View =
                inflater.inflate(R.layout.new_task_layout, null)
            val taskText = dialogView.findViewById<EditText>(R.id.taskText)
            val isPermanent = dialogView.findViewById<CheckBox>(R.id.isPermanent)

            // Inflate and set the layout for the dialog.
            // Pass null as the parent view because it's going in the dialog layout.
            builder.setView(dialogView)
                .setPositiveButton(
                    "Add task"
                ) { _, _ ->
                    // add task to room

                    taskDialogListener.taskData(
                        taskText.editableText.toString(),
                        isPermanent?.isChecked ?: false
                    )
                }
                .setNegativeButton(
                    "cancel"
                ) { _, _ ->
                    dialog?.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")

    }
}


interface TaskDialogListener {
    fun taskData(taskText: String, isPermanent: Boolean)
}