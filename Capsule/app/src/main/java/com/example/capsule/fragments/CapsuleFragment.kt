package com.example.capsule.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.capsule.adapters.CollectionAdapter
import com.example.capsule.databinding.FragmentCapsuleBinding
import com.example.capsule.utility.Utils
import com.example.capsule.viewModels.CapsuleViewModel
import com.google.android.material.tabs.TabLayoutMediator

class CapsuleFragment : Fragment() {

    private lateinit var binding: FragmentCapsuleBinding
    private lateinit var viewPager: ViewPager2
    private lateinit var collectionAdapter: CollectionAdapter
    private lateinit var viewModel: CapsuleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCapsuleBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[CapsuleViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectionAdapter = CollectionAdapter(this)
        viewPager = binding.pager
        viewPager.adapter = collectionAdapter

        val fragmentNames = Utils.fragmentNames
        viewModel.startTimer()

        val tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = fragmentNames[position]
        }.attach()

        viewModel.milliSecondsLeft.observe(viewLifecycleOwner) {
            binding.timerTextView.text = it
        }
    }
}