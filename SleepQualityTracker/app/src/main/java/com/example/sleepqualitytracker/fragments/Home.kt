package com.example.sleepqualitytracker.fragments

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sleepqualitytracker.SleepRecyclerView
import com.example.sleepqualitytracker.SleepViewModel
import com.example.sleepqualitytracker.SleepViewModelFactory
import com.example.sleepqualitytracker.databinding.FragmentHomeBinding
import com.example.sleepqualitytracker.room.SleepEntity
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
class Home : Fragment() {
    private lateinit var night: SleepEntity
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: SleepViewModel
    private val args: HomeArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(viewModelStore,
            SleepViewModelFactory(requireActivity().applicationContext))[SleepViewModel::class.java]
        return binding.root
    }

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sleepRecyclerView.layoutManager = LinearLayoutManager(requireActivity().applicationContext)

        binding.btnStart.setOnClickListener {
            binding.btnStop.isEnabled = true
            binding.btnStart.isEnabled = false
            night = viewModel.createNewSleep()

            binding.startTime.text = SimpleDateFormat("EEE MMM-d-yyyy 'Time:' HH:mm").format(night.startTimeMillis)
            binding.startTimeLinearLayout.visibility = View.VISIBLE
        }

        binding.btnStop.setOnClickListener {
            val endDateTime = viewModel.getCurrentDateTimeInfo()
            night.endTimeMillis = endDateTime.time

            val action = HomeDirections.actionHome2ToRating(night)
            findNavController().navigate(action)

            binding.btnStop.isEnabled = false
            binding.btnStart.isEnabled = true
        }


        val sleepObject = args.sleepObject
        if (sleepObject?.quality!=-1){
            if (sleepObject != null) {
                viewModel.insertNewNight(sleepObject)
            }
        }

        binding.btnClearAll.setOnClickListener {
            Toast.makeText(requireActivity().applicationContext, "Clearing all data", Toast.LENGTH_SHORT).show()
            viewModel.clearAllSleepData()
        }

        viewModel.allNights.observe(viewLifecycleOwner){
            binding.sleepRecyclerView.adapter = SleepRecyclerView(it, requireActivity().applicationContext)
        }
    }
}