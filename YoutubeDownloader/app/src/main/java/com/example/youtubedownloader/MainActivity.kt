package com.example.youtubedownloader

//noinspection SuspiciousImport
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.youtubedownloader.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //request the permission.

//        ActivityCompat.requestPermissions(
//            this,
//            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
//            1
//        )
    }

}