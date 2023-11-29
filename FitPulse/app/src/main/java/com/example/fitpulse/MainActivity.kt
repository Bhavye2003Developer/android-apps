package com.example.fitpulse

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.fitpulse.databinding.ActivityMainBinding
import com.example.fitpulse.viewModels.QuoteViewModel
import com.google.android.material.slider.Slider


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: QuoteViewModel

    private lateinit var popupWindow: PopupWindow
    private lateinit var waterUnit: String

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[QuoteViewModel::class.java]
        setContentView(binding.root)

        viewModel.getQuote()
        viewModel.quoteInfo.observe(this) {
            binding.quote.text = "\"${it?.get(0)?.quote}\""
            binding.author.text = "By ${it?.get(0)?.author}"
        }
        setUpHourDropdown()
        setMinutesDropdown()
        setWaterUnitDropdown()

        binding.saveSleepButton.setOnClickListener {
            val hrs = binding.hoursInputDropdown.selectedItem.toString().split(" ")[0].toInt()
            val minutes = binding.minutesInputDropdown.selectedItem.toString().split(" ")[0].toInt()
            val sleep = "Sleep: $hrs hours and $minutes minutes"
            var waterInTake = "Water Intake: "
            waterInTake += if (waterUnit == "glass") {
                val amount = (binding.waterProgressBar.value.toInt() / 10)
                "$amount glasses"
            } else {
                val amount = (binding.waterProgressBar.value.toInt() * 100)
                "$amount milliliters"
            }
            binding.result.text = "RESULT\n$sleep\n$waterInTake"
        }

        binding.quote.setOnClickListener {
            if (binding.quote.maxLines == 2) {
                binding.quote.maxLines = Integer.MAX_VALUE
            } else {
                binding.quote.maxLines = 2
            }
        }
    }

    private fun setUpHourDropdown() {
        val hrs = ArrayList<String>()
        for (hr in 1..24) {
            hrs.add("$hr hrs")
        }
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        val adapter: ArrayAdapter<Any?> =
            ArrayAdapter<Any?>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                hrs as List<Any?>
            )
        //set the spinners adapter to the previously created one.
        binding.hoursInputDropdown.adapter = adapter
    }

    private fun setMinutesDropdown() {
        val minutes = ArrayList<String>()
        for (min in 1..59) {
            minutes.add("$min minutes")
        }
        val adapter: ArrayAdapter<Any?> =
            ArrayAdapter<Any?>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                minutes as List<Any?>
            )
        //set the spinners adapter to the previously created one.
        binding.minutesInputDropdown.adapter = adapter
    }

    @SuppressLint("InflateParams")
    private fun setWaterUnitDropdown() {
        val units = arrayListOf("glass", "ml")
        val adapter: ArrayAdapter<Any?> =
            ArrayAdapter<Any?>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                units as List<Any?>
            )
        //set the spinners adapter to the previously created one.
        binding.waterUnitDropdown.adapter = adapter

        binding.waterUnitDropdown.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View?,
                position: Int, id: Long
            ) {
                waterUnit = parent.getItemAtPosition(position) as String
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        // Inflate the layout for the popup window
        val popupView = LayoutInflater.from(this).inflate(R.layout.popup_layout, null)
        val popupText = popupView.findViewById<TextView>(R.id.popupText)

        // Initialize the PopupWindow
        popupWindow = PopupWindow(
            popupView,
            resources.getDimensionPixelSize(R.dimen.popup_width),
            resources.getDimensionPixelSize(R.dimen.popup_height),
            true
        )

        val slider = binding.waterProgressBar

        slider.addOnSliderTouchListener(object :
            Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {
            }

            override fun onStopTrackingTouch(slider: Slider) {
                popupWindow.dismiss()
            }
        }
        )

        slider.addOnChangeListener { _, value, _ ->
            popupText.text = showProgressPerUnit(waterUnit, value.toInt())
            showPopup(binding.waterProgressBar)
        }
    }

    private fun showPopup(anchorView: Slider) {
        // Show the popup above the SeekBar
        popupWindow.showAsDropDown(anchorView, 0, -anchorView.height)
    }

    private fun showProgressPerUnit(unit: String, progress: Int): String {
        if (unit == "glass") {
            return "Glass: ${(progress / 10)}"
        }
        return "ml: ${progress * 100}"
    }
}