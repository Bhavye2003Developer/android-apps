package com.example.colormyviews

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.colormyviews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
    }

    private fun setListeners() {
        val buttons = arrayListOf(binding.btnBox1, binding.btnBox2, binding.btnBox3, binding.btnBox4,
            binding.btnBox5, binding.btnRed, binding.btnYellow, binding.btnGreen)
        buttons.forEach { textView ->
            textView.setOnClickListener {
                makeColor(it)
            }
        }
    }

    private fun makeColor(it: View) {
        when(it){

            binding.btnBox1 -> binding.btnBox1.setBackgroundColor(Color.DKGRAY)
            binding.btnBox2 -> binding.btnBox2.setBackgroundColor(Color.GRAY)
            binding.btnBox3 -> binding.btnBox3.setBackgroundResource(android.R.color.holo_green_light)
            binding.btnBox4 -> binding.btnBox4.setBackgroundResource(android.R.color.holo_green_dark)
            binding.btnBox5 -> binding.btnBox5.setBackgroundResource(android.R.color.holo_green_light)
            binding.btnRed -> binding.btnBox3.setBackgroundResource(R.color.my_red)
            binding.btnGreen -> binding.btnBox5.setBackgroundResource(R.color.my_green)
            binding.btnYellow -> binding.btnBox4.setBackgroundResource(R.color.my_yellow)

//            else -> it.setBackgroundColor(Color.LTGRAY)
        }
    }
}