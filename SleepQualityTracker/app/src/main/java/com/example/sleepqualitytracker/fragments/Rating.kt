package com.example.sleepqualitytracker.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sleepqualitytracker.SleepViewModel
import com.example.sleepqualitytracker.SleepViewModelFactory
import com.example.sleepqualitytracker.databinding.FragmentRatingBinding

class Rating : Fragment(){

    private lateinit var binding: FragmentRatingBinding
    private lateinit var viewModel: SleepViewModel
    private val args: RatingArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRatingBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(
            viewModelStore, SleepViewModelFactory(requireActivity().applicationContext)
        )[SleepViewModel::class.java]
        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rateArray = arrayListOf(binding.i0, binding.i1, binding.i2, binding.i3, binding.i4, binding.i5)

        var rating: Int

        rateArray.forEach { imageView ->
            imageView.setOnClickListener {
                rating = it.tooltipText.toString().toInt()
                updateNight(rating)
            }
        }
    }
    private fun updateNight(rating: Int){
        // update the data
        val sleepObject = args.sleepObject
        sleepObject.quality = rating
        val action = RatingDirections.actionRatingToHome2(sleepObject)
        findNavController().navigate(action)
    }
}