package com.example.nasafetcher.appfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.nasafetcher.R
import com.example.nasafetcher.databinding.NeoFragmentBinding


class NeoHome : Fragment(R.layout.neo_fragment) {
    private lateinit var binding: NeoFragmentBinding

    private var isCalStartVisible = false
    private var curStartDate: String = ""
    private var curEndDate: String = ""
    private var btnStartEndArr: Array<Boolean> =
        arrayOf(false, false)  // none of the buttons are pressed.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NeoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.calView.visibility = View.GONE

        binding.btnStartDate.setOnClickListener {
            btnStartEndArr[0] = true // start date button pressed.
            btnStartEndArr[1] = false

            setCalView()
        }


        binding.btnEndDate.setOnClickListener {
            btnStartEndArr[0] = false
            btnStartEndArr[1] = true // end date button pressed.

            setCalView()
        }


        binding.btnSearchNeo.setOnClickListener {
            if (curStartDate != "" && curEndDate != "") {
                Toast.makeText(
                    requireActivity().applicationContext,
                    "$curStartDate and $curEndDate",
                    Toast.LENGTH_SHORT
                )
                    .show()
            } else {
                Toast.makeText(
                    requireActivity().applicationContext,
                    "Start Date or End Date not selected", Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.calView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            if (btnStartEndArr[0] && !btnStartEndArr[1]) {// start date has to be selected
                curStartDate = "$year-${month + 1}-$dayOfMonth"
            } else {
                curEndDate = "$year-${month + 1}-$dayOfMonth"
            }
            binding.calView.visibility = View.GONE
            isCalStartVisible = false
        }
    }

    private fun setCalView() {
        binding.calView.visibility = View.VISIBLE
        isCalStartVisible = true
    }
}