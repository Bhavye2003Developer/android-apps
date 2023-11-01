package com.example.aboutmeudacity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nicknameEdit = findViewById<EditText>(R.id.nickname)
        val result = findViewById<TextView>(R.id.nickname_result)
        val btnDone = findViewById<Button>(R.id.btnDone)

        btnDone.setOnClickListener {
            result.text = nicknameEdit.editableText.toString()
            result.visibility = View.VISIBLE
            btnDone.visibility = View.GONE
            nicknameEdit.visibility = View.GONE
        }
    }
}