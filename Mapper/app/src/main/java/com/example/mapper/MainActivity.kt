package com.example.mapper

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mapper.databinding.ActivityMainBinding
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.LineData

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val entries = ArrayList<Entry>()
    private lateinit var chart: LineChart
    private lateinit var dataSet: LineDataSet
    private lateinit var lineData: LineData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chart = binding.chart

        chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        chart.xAxis.textColor = Color.WHITE
        chart.axisLeft.textColor = Color.WHITE
        chart.axisRight.textColor = Color.WHITE

        plotPoint(0f, 0f)

        binding.btnPlot.setOnClickListener {
            var x = 0f
            var y = 0f
            if (binding.xAxis.editableText.isNotEmpty()) {
                x = binding.xAxis.editableText.toString().toFloat()
            }
            if (binding.yAxis.editableText.isNotEmpty()) {
                y = binding.yAxis.editableText.toString().toFloat()
            }
            binding.xAxis.text.clear()
            binding.yAxis.text.clear()

            plotPoint(x, y)
        }
    }

    private fun plotPoint(x: Float = 0f, y: Float = 0f) {
        entries.add(Entry(x, y))
        if (!::dataSet.isInitialized) {
            dataSet = LineDataSet(entries, "") // add entries to dataset
            lineData = LineData(dataSet)
            chart.data = lineData
        } else {
            dataSet.notifyDataSetChanged()
            lineData.notifyDataChanged()
            chart.notifyDataSetChanged()
        }

        // Adjust the visible range to show all points
        val visibleRange = 10f // You can adjust this value based on your preference
        chart.setVisibleXRangeMaximum(visibleRange)

        // Move the view to the latest point
        chart.moveViewToX(x)

        chart.invalidate() // refresh
    }
}