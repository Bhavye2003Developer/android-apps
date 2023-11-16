package com.example.youtubedownloader.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.media.MediaScannerConnection
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import java.io.File

class VideoDeleteDialog(private val videoPath: String, private val activity: FragmentActivity) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction.
            val builder = AlertDialog.Builder(it)
            builder.setMessage("Do you want to delete this video?")
                .setPositiveButton("OK") { _, _ ->
                    // START THE GAME!
                    val file = File(videoPath)
                    file.delete()
                    MediaScannerConnection.scanFile(
                        context,
                        arrayOf(videoPath),  // deleted file path
                        null
                    ) { _, _ -> // path, uri
                        // Scan completed
                        // The media provider database has been updated
                    }
                    timer.start()
                }
                .setNegativeButton("CANCEL") { _, _ ->
                    // User cancelled the dialog.
                }
            // Create the AlertDialog object and return it.
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private val timer = object: CountDownTimer(3000, 1000) {
        override fun onTick(millisUntilFinished: Long) {

        }
        override fun onFinish() {
            activity.recreate()
        }
    }
}