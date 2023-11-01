package com.example.nasafetcher.appfragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nasafetcher.databinding.FragmentSatellitePositionBinding
import com.example.nasafetcher.network.satSearch.SatService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.properties.Delegates

class SatellitePosition : Fragment() {

    private lateinit var binding: FragmentSatellitePositionBinding
    private var latitude by Delegates.notNull<Double>()
    private var longitude by Delegates.notNull<Double>()

    override fun onCreateView(


        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSatellitePositionBinding.inflate(inflater, container, false)
        return binding.root
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.search.setOnClickListener {
            val satelliteId = binding.satSearch.editableText.toString()

            CoroutineScope(Dispatchers.IO).launch {
                val satData = async { SatService.getSatData(satelliteId) }
                val data = satData.await()
                withContext(Dispatchers.Main) {
                    latitude = data.geodetic.latitude
                    longitude = data.geodetic.longitude
                    binding.result.text = "Latitude : $latitude\nLongitude: $longitude"
                }
            }
        }
    }

}