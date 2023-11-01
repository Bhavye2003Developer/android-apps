package com.example.nasafetcher.appfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.nasafetcher.databinding.HomeFragmentBinding
import com.example.nasafetcher.network.astronomyPicDay.Api
import com.example.nasafetcher.network.astronomyPicDay.JsonFormat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Home : Fragment() {
    private lateinit var binding: HomeFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val job = CoroutineScope(Dispatchers.IO).async {
            Api.getJsonData()
        }

        CoroutineScope(Dispatchers.IO).launch {
            val json: JsonFormat = job.await()
            withContext(Dispatchers.Main) {
                Glide.with(requireActivity().applicationContext)
                    .load(json.hdurl)
                    .into(binding.astronomyPicDay)
            }
        }

        binding.btnSpaceNews.setOnClickListener {
            Toast.makeText(
                requireActivity().applicationContext,
                "Opening Space News...",
                Toast.LENGTH_SHORT
            ).show()
            val action = HomeDirections.actionHome2ToSpaceNews()
            findNavController().navigate(action)
        }

        binding.btnPosSat.setOnClickListener {
            Toast.makeText(
                requireActivity().applicationContext,
                "Opening Satellite position...",
                Toast.LENGTH_SHORT
            ).show()
            val action = HomeDirections.actionHome2ToSatellitePosition()
            findNavController().navigate(action)
        }
    }
}