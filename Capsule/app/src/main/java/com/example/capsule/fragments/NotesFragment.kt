package com.example.capsule.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.capsule.databinding.FragmentNotesBinding
import com.example.capsule.utility.Utils

class NotesFragment : Fragment() {

    private lateinit var binding: FragmentNotesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pdf = Utils.pdf

        val webView = binding.notes
        webView.settings.javaScriptEnabled = true

        webView.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=$pdf")
    }
}