package com.example.marsalbum

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.marsalbum.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MarsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MarsViewModel::class.java]

        viewModel.getData()

        viewModel.response.observe(this){
            binding.marsItemsRecyclerView.adapter = MarsRecyclerViewAdapter(this, it)
//            binding.marsItemsRecyclerView.setHasFixedSize(true)
        }
    }
}