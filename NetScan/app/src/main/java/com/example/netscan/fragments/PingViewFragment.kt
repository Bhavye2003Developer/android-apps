package com.example.netscan.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.netscan.viewModels.PingViewModel
import com.example.netscan.databinding.FragmentPingViewBinding

class PingViewFragment : Fragment() {

    private lateinit var binding: FragmentPingViewBinding
    private lateinit var viewModel: PingViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPingViewBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[PingViewModel::class.java]
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPing.setOnClickListener {
            val url = binding.pingUrl.editableText.toString()
            binding.pingUrl.text?.clear()
            viewModel.pingIP(url)
        }

        viewModel.result.observe(viewLifecycleOwner){
            binding.pingResult.text = it
            Log.d("tester", it)
        }
    }
}