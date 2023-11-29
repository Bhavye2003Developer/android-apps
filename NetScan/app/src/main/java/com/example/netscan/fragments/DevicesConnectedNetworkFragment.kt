package com.example.netscan.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.netscan.DevicesConnectedToNetwork
import com.example.netscan.R
import com.example.netscan.databinding.FragmentDevicesConnectedNetworkBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DevicesConnectedNetworkFragment : Fragment() {

    private lateinit var binding: FragmentDevicesConnectedNetworkBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDevicesConnectedNetworkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val devicesConnectedToNetwork = DevicesConnectedToNetwork(requireActivity())

        CoroutineScope(Dispatchers.Default).launch {
            devicesConnectedToNetwork.getDevices()
        }
    }

}