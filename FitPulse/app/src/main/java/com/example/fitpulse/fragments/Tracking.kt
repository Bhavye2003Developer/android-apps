package com.example.fitpulse.fragments

//noinspection SuspiciousImport
import android.R
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.PopupWindow
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fitpulse.Exercise
import com.example.fitpulse.adapters.ExerciseDropDownAdapter
import com.example.fitpulse.databinding.FragmentTrackingBinding
import com.example.fitpulse.viewModels.QuoteViewModel
import com.google.android.material.slider.Slider

class Tracking : Fragment() {
    private lateinit var binding: FragmentTrackingBinding
    private lateinit var viewModel: QuoteViewModel

    private lateinit var popupWindow: PopupWindow
    private var waterUnit: String = ""

    private val exercises = arrayOf(
        Exercise("Running"),
        Exercise("Cycling"),
        Exercise("Walking"),
        Exercise("Swimming"),
        Exercise("Weightlifting"),
        Exercise("Yoga"),
        Exercise("Pilates"),
        Exercise("Jump Rope"),
        Exercise("Rowing"),
        Exercise("Hiking"),
        Exercise("Aerobics"),
        Exercise("Dancing"),
        Exercise("Martial Arts"),
        Exercise("Skiing"),
        Exercise("Snowboarding"),
        Exercise("Elliptical"),
        Exercise("Other")
    )
    private lateinit var context: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTrackingBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[QuoteViewModel::class.java]
        context = requireActivity().applicationContext
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getQuote()
        viewModel.quoteInfo.observe(viewLifecycleOwner) {
            binding.quote.text = "\"${it?.get(0)?.quote}\""
            binding.author.text = "By ${it?.get(0)?.author}"
        }
        setUpHourDropdown(binding.hoursInputDropdown)
        setMinutesDropdown(binding.minutesInputDropdown)
        setWaterUnitDropdown()

        setUpHourDropdown(binding.exerciseHoursInputDropdown)
        setMinutesDropdown(binding.exerciseMinutesInputDropdown)
        exerciseTypeDropdown()

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
            var checkedExercises = "Exercises done:\n"
            exercises.forEach {
                if (it.isChecked) {
                    checkedExercises += "${it.exerciseName}\n"
                }
            }
            binding.result.text = "RESULT\n$sleep\n$waterInTake\n$checkedExercises"

            binding.sleepSectionView.visibility = View.GONE
            binding.waterSectionView.visibility = View.GONE
            binding.exerciseSectionView.visibility = View.GONE
            binding.quote.maxLines = 2
        }

        binding.quote.setOnClickListener {
            if (binding.quote.maxLines == 2) {
                binding.quote.maxLines = Integer.MAX_VALUE
            } else {
                binding.quote.maxLines = 2
            }
        }

        binding.cardViewSleep.setOnClickListener {
            if (binding.sleepSectionView.visibility == View.GONE) {
                binding.sleepSectionView.visibility = View.VISIBLE
            } else {
                binding.sleepSectionView.visibility = View.GONE
            }
        }

        binding.cardViewWater.setOnClickListener {
            if (binding.waterSectionView.visibility == View.GONE) {
                binding.waterSectionView.visibility = View.VISIBLE
            } else {
                binding.waterSectionView.visibility = View.GONE
            }
        }

        binding.cardViewExercise.setOnClickListener {
            if (binding.exerciseSectionView.visibility == View.GONE) {
                binding.exerciseSectionView.visibility = View.VISIBLE
            } else {
                binding.exerciseSectionView.visibility = View.GONE
            }
        }
    }

    private fun setUpHourDropdown(dropDown: Spinner) {
        val hrs = ArrayList<String>()
        for (hr in 1..24) {
            hrs.add("$hr hrs")
        }
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        val adapter: ArrayAdapter<Any?> =
            ArrayAdapter<Any?>(
                context,
                R.layout.simple_spinner_dropdown_item,
                hrs as List<Any?>
            )
        //set the spinners adapter to the previously created one.
        dropDown.adapter = adapter
    }

    private fun setMinutesDropdown(dropDown: Spinner) {
        val minutes = ArrayList<String>()
        for (min in 1..59) {
            minutes.add("$min minutes")
        }
        val adapter: ArrayAdapter<Any?> =
            ArrayAdapter<Any?>(
                context,
                R.layout.simple_spinner_dropdown_item,
                minutes as List<Any?>
            )
        //set the spinners adapter to the previously created one.
        dropDown.adapter = adapter
    }

    @SuppressLint("InflateParams")
    private fun setWaterUnitDropdown() {
        val units = arrayListOf("glass", "ml")
        val adapter: ArrayAdapter<Any?> =
            ArrayAdapter<Any?>(
                context,
                R.layout.simple_spinner_dropdown_item,
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
        val popupView =
            LayoutInflater.from(context).inflate(com.example.fitpulse.R.layout.popup_layout, null)
        val popupText = popupView.findViewById<TextView>(com.example.fitpulse.R.id.popupText)

        // Initialize the PopupWindow
        popupWindow = PopupWindow(
            popupView,
            com.example.fitpulse.R.dimen.popup_width,
            com.example.fitpulse.R.dimen.popup_height,
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
        })

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

    private fun exerciseTypeDropdown() {
        val adapter = ExerciseDropDownAdapter(
            context,
            com.example.fitpulse.R.layout.exercise_item_layout,
            exercises
        )
        binding.exerciseTypeDropdown.adapter = adapter
    }
}