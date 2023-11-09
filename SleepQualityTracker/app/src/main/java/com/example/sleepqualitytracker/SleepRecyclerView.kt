package com.example.sleepqualitytracker

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sleepqualitytracker.room.SleepEntity
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit

@SuppressLint("SimpleDateFormat")
class SleepRecyclerView(private val allNights: List<SleepEntity>, private val context: Context) : RecyclerView.Adapter<SleepRecyclerView.ViewHolder>() {
    private val formatterDate = SimpleDateFormat("EEE MMM-d-yyyy 'Time:' HH:mm")

    private val ratings = arrayListOf("Very poor", "Poor", "Fair", "Good", "Pretty good", "Excellent!")
    private val rateColors = arrayListOf(R.color.colorVeryPoor, R.color.colorPoor, R.color.colorFair,
        R.color.colorGood, R.color.colorPrettyGood, R.color.colorExcellent)
    class ViewHolder(view:View) : RecyclerView.ViewHolder(view){
        val startTime: TextView = view.findViewById(R.id.startTime)
        val endTime: TextView = view.findViewById(R.id.endTime)
        val quality: TextView = view.findViewById(R.id.quality)
        val hms: TextView = view.findViewById(R.id.hms)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sleep_item, parent, false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val night = allNights[position]
        holder.startTime.text = formatterDate.format(night.startTimeMillis)
        holder.endTime.text = formatterDate.format(night.endTimeMillis)
        holder.quality.text = ratings[night.quality]

        val durationMillis = night.endTimeMillis - night.startTimeMillis

        val hours = TimeUnit.MILLISECONDS.toHours(durationMillis)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(durationMillis) % 60
        val seconds = TimeUnit.MILLISECONDS.toSeconds(durationMillis) % 60

        // Format the result as hh:mm:ss
        val formattedDuration = String.format("%02d:%02d:%02d", hours, minutes, seconds)
        holder.hms.text = formattedDuration

        holder.quality.setTextColor(ContextCompat.getColor(context, rateColors[night.quality]))
    }

    override fun getItemCount() = allNights.size
}