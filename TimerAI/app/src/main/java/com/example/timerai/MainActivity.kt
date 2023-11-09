package com.example.timerai

import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Bundle
import android.os.CountDownTimer
import android.text.format.DateUtils
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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

        binding.btnPauseTimer.setOnClickListener {
            viewModel.stopTimer()
            Toast.makeText(this, "Timer paused", Toast.LENGTH_SHORT).show()
            binding.btnNewTimer.isEnabled = true
            binding.btnPauseTimer.isEnabled = false
            binding.btnResumeTimer.isEnabled = true
        }

        binding.btnResumeTimer.setOnClickListener {
            Toast.makeText(this, "Timer resumed", Toast.LENGTH_SHORT).show()
            viewModel.resumeTimer()
            binding.btnPauseTimer.isEnabled = true
            binding.btnResumeTimer.isEnabled = false
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
                        binding.btnPauseTimer.isEnabled = false
                        binding.btnResumeTimer.isEnabled = false
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
        binding.btnPauseTimer.isEnabled = true

        viewModel.timeText.observe(this) {
            if (it < 10) {
                binding.timerCountdown.setTextColor(ContextCompat.getColor(this, R.color.red))
            } else {
                binding.timerCountdown.setTextColor(ContextCompat.getColor(this, R.color.white))
            }
            binding.timerCountdown.text = DateUtils.formatElapsedTime(it.toLong()).toString()
        }
    }
}