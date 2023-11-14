package com.example.skyshare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.skyshare.R
import com.example.skyshare.databinding.FragmentReceiverBinding
import com.example.skyshare.viewModels.SenderViewModel

class Receiver : Fragment() {

    private lateinit var binding: FragmentReceiverBinding
    private lateinit var viewModel: SenderViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentReceiverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.wifip2pEnabled.observe(viewLifecycleOwner){
            if (it) {
                Toast.makeText(
                    requireActivity().applicationContext,
                    "WifiP2P enabled",
                    Toast.LENGTH_SHORT
                ).show()
                binding.isWifiP2pEnabled.setBackgroundResource(R.color.green)

//                viewModel.initiatePeerDiscovery()
            }
            else{
                Toast.makeText(requireActivity().applicationContext, "WifiP2P disabled!", Toast.LENGTH_SHORT).show()
                binding.isWifiP2pEnabled.setBackgroundResource(R.color.red)
            }
        }
    }
}