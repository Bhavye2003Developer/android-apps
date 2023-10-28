package com.example.revision

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.revision.databinding.HomeFragmentBinding

class HomeFragment : Fragment(R.layout.home_fragment) {

    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        return binding.root
//        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnToDiceFragment.setOnClickListener {
            Toast.makeText(activity, "Opening Dice Roller...", Toast.LENGTH_SHORT).show()
            val action = HomeFragmentDirections.actionHomeFragmentToDiceRollFragment()
            findNavController().navigate(action)
        }

        binding.btnToQuizActivity.setOnClickListener {
            Toast.makeText(activity, "Opening Quiz...", Toast.LENGTH_SHORT).show()
            val action = HomeFragmentDirections.actionHomeFragmentToQuizActivity()
            findNavController().navigate(action)
        }

        binding.btnToActivityLC.setOnClickListener {
            Toast.makeText(activity, "Opening Activity Lifecycle...", Toast.LENGTH_SHORT).show()
            val action = HomeFragmentDirections.actionHomeFragmentToActivityLc()
            findNavController().navigate(action)
        }
    }
}