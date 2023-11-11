package com.example.aura.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aura.analysers.Text
import com.example.aura.databinding.FragmentAnalyseTextBinding

class AnalyseTextFragment : Fragment() {

    private lateinit var viewBinding: FragmentAnalyseTextBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewBinding = FragmentAnalyseTextBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textAnalyser = Text()
        textAnalyser.analyse(::onFailure)
    }

    fun onFailure(err: String) {
        Log.d("analyse", err)
    }

}