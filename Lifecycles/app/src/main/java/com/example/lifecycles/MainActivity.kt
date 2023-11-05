package com.example.lifecycles

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
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
        Toast.makeText(this, "OnCreate called", Toast.LENGTH_SHORT).show()

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
        Toast.makeText(this, "OnSavedInstanceState called", Toast.LENGTH_SHORT).show()
        outState.putInt(COUNTER_TAG, counter)
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "OnStart called", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "OnResume called", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this, "OnPause called", Toast.LENGTH_SHORT).show()
    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(this, "OnRestart called", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "OnStop called", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "OnDestroy called", Toast.LENGTH_SHORT).show()
    }

}