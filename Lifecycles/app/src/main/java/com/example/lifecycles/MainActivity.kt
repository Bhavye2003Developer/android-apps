package com.example.lifecycles

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btnInc: Button
    private lateinit var cntText: TextView
    private var counter = 0
    private val COUNTER_TAG = "Counter"

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState!=null){
            counter = savedInstanceState.getInt(COUNTER_TAG)
        }

        setContentView(R.layout.activity_main)

        btnInc = findViewById(R.id.btnInc)
        cntText = findViewById(R.id.cntText)
        cntText.text = counter.toString()

        btnInc.setOnClickListener {
            ++counter
            cntText.text = (counter).toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(COUNTER_TAG, counter)
    }

}