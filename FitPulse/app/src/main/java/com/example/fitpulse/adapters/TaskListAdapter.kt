package com.example.fitpulse.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpulse.R
import com.example.fitpulse.room.dailyTasks.DailyTask


class TaskListAdapter(
    private val taskList: List<DailyTask>,
    private val removeTask: (DailyTask) -> Unit,
    private val updateTaskToDone: (Int) -> Unit,
    private val updateTaskToLongTerm: (Int) -> Unit
) :
    RecyclerView.Adapter<TaskListAdapter.ItemViewHolder>() {
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val taskText: TextView = view.findViewById(R.id.item_taskText)
        val taskItem: LinearLayout = view.findViewById(R.id.taskItem)
        val taskOptions: LinearLayout = view.findViewById(R.id.taskOptions)
        val btnToLongTermGoal: Button = view.findViewById(R.id.btnMakeLongTermGoal)
        val btnDone: Button = view.findViewById(R.id.btnDone)
        val btnRemove: Button = view.findViewById(R.id.btnRemove)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
//         create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int = taskList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val taskItem = taskList[position]
        holder.taskText.text = taskItem.taskText

        if (taskItem.isTaskDone) {
            holder.taskText.paintFlags = holder.taskText.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }

        holder.taskItem.setOnClickListener {
            if (holder.taskOptions.visibility == View.VISIBLE) {
                holder.taskOptions.visibility = View.GONE
            } else {
                holder.taskOptions.visibility = View.VISIBLE
            }
        }
        holder.btnRemove.setOnClickListener {
            removeTask(taskItem)
        }
        holder.btnDone.setOnClickListener {
            updateTaskToDone(taskItem.id)
        }
        holder.btnToLongTermGoal.setOnClickListener {
            updateTaskToLongTerm(taskItem.id)
        }
    }
}