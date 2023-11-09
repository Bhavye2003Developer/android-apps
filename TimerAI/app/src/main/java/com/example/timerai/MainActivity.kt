package com.example.timerai

import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.timerai.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), TimeCounterDialog.NoticeDialogListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TimeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[TimeViewModel::class.java]

        binding.btnNewTimer.setOnClickListener {
            TimeCounterDialog().show(supportFragmentManager, "Set time")
        }

        viewModel.isFinished.observe(this) {
            if (viewModel.isFinished.value == true) {
                binding.btnNewTimer.isEnabled = true
                val alarmObj = alarm()
                alarmObj.play()
                object : CountDownTimer(5000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                    }

                    override fun onFinish() {
                        alarmObj.stop()
                        viewModel.isFinished.value = false
                    }
                }.start()
            }
        }
    }

    private fun alarm(): Ringtone {
        val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        return RingtoneManager.getRingtone(applicationContext, notification)
    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        val time: Int = try {
            dialog.dialog?.findViewById<EditText>(R.id.timeToCount)?.editableText.toString()
                .toInt()
        } catch (e: Exception) {
            0
        }
        Toast.makeText(this, "Starting timer...", Toast.LENGTH_SHORT).show()
        viewModel.startTimer(time)
        binding.btnNewTimer.isEnabled = false

        viewModel.timeText.observe(this) {
            binding.timerCountdown.text = it
        }
    }
}