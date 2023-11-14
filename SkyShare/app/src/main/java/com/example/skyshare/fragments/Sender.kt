package com.example.skyshare.fragments

import android.annotation.SuppressLint
import android.net.wifi.p2p.WifiP2pDevice
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.skyshare.PeersRecyclerViewAdapter
import com.example.skyshare.R
import com.example.skyshare.databinding.FragmentSenderBinding
import com.example.skyshare.viewModels.SenderViewModel
import com.example.skyshare.viewModels.SenderViewModelFactory

class Sender : Fragment() {

    private lateinit var binding: FragmentSenderBinding
    private lateinit var viewModel: SenderViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSenderBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, SenderViewModelFactory(requireActivity().application))[SenderViewModel::class.java]
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.peerList.setHasFixedSize(true)

        viewModel.wifip2pEnabled.observe(viewLifecycleOwner){
            if (it) {
                Toast.makeText(
                    requireActivity().applicationContext,
                    "WifiP2P enabled",
                    Toast.LENGTH_SHORT
                ).show()
                binding.isWifiP2pEnabled.setBackgroundResource(R.color.green)

                viewModel.initiatePeerDiscovery()
            }
            else{
                Toast.makeText(requireActivity().applicationContext, "WifiP2P disabled!", Toast.LENGTH_SHORT).show()
                binding.isWifiP2pEnabled.setBackgroundResource(R.color.red)
            }
        }

        viewModel.peersList.observe(viewLifecycleOwner){
            if (it.size==0){
                binding.errorMessage.text = "No Device Found"
                binding.errorMessage.visibility = View.VISIBLE
                binding.peerList.visibility = View.INVISIBLE
            }
            else{
                binding.peerList.adapter = PeersRecyclerViewAdapter(it, ::connect)
                binding.errorMessage.visibility = View.GONE
                binding.peerList.visibility = View.VISIBLE
            }
        }
    }

    private fun connect(device: WifiP2pDevice){
        viewModel.connectDevice(device)
    }

    override fun onResume() {
        super.onResume()
        viewModel.registerReceiver()
    }

    override fun onPause() {
        super.onPause()
        viewModel.unregisterReceiver()
    }
}