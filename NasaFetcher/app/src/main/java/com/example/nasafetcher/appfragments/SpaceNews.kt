package com.example.nasafetcher.appfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nasafetcher.adapters.SpaceNews
import com.example.nasafetcher.databinding.FragmentSpaceNewsBinding
import com.example.nasafetcher.network.spaceNews.Api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SpaceNews : Fragment() {

    private lateinit var binding: FragmentSpaceNewsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSpaceNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.setHasFixedSize(true)

        CoroutineScope(Dispatchers.IO).launch {
            val job = async { Api.getData() }
            val data = job.await()

            // Data ready to be shown
            withContext(Dispatchers.Main) {
                binding.progressBar.visibility = View.GONE
                binding.recyclerView.adapter = SpaceNews(requireActivity().applicationContext, data)
            }
        }
    }
}