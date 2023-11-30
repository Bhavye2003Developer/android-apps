package com.example.fitpulse.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import com.example.fitpulse.Exercise
import com.example.fitpulse.R


class ExerciseDropDownAdapter(
    context: Context,
    resource: Int,
    private val exercises: Array<Exercise>
) :
    ArrayAdapter<Exercise>(context, resource, exercises) {

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return rowView(convertView, position)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return rowView(convertView, position)
    }

    @SuppressLint("InflateParams")
    private fun rowView(convertView: View?, position: Int): View {

        var view = convertView
        var holder = ViewHolder(context)
        if (view == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.exercise_item_layout, null, false)

            holder.exerciseCheckBox = view.findViewById(R.id.exerciseItem)
            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }
        holder.exerciseCheckBox.text = exercises[position].exerciseName
        holder.exerciseCheckBox.setOnCheckedChangeListener { _, p1 ->
            exercises[position].isChecked = p1
        }
        return view!!
    }

    private class ViewHolder(context: Context) {
        var exerciseCheckBox: CheckBox = CheckBox(context)
    }
}