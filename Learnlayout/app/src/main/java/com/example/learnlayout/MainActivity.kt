package com.example.learnlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDark = findViewById<Button>(R.id.btnDark)
        val btnLight = findViewById<Button>(R.id.btnLight)
        val baseActivity = findViewById<ConstraintLayout>(R.id.base)
        val heading = findViewById<TextView>(R.id.heading)

        // on activity launch
        baseActivity.setBackgroundResource(R.color.black)
        heading.setTextColor(getColor(R.color.white))

        btnDark.setOnClickListener {
            baseActivity.setBackgroundResource(R.color.black)
            heading.setTextColor(getColor(R.color.white))
        }

        btnLight.setOnClickListener {
            baseActivity.setBackgroundResource(R.color.white)
            heading.setTextColor(getColor(R.color.black))
        }
    }
}