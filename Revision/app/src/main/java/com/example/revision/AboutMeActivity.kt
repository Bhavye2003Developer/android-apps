package com.example.revision

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.revision.databinding.ActivityAboutMeBinding

class AboutMeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutMeBinding
    private val MY_GITHUB_LINK = "https://github.com/Bhavye2003Developer/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAboutMeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.myGithub.setOnClickListener {
            Toast.makeText(this, "Showing my github...", Toast.LENGTH_SHORT).show()
            searchWeb(MY_GITHUB_LINK)
        }
    }

    private fun searchWeb(query: String) {
        val intent = Intent(Intent.ACTION_WEB_SEARCH).apply {
            putExtra(SearchManager.QUERY, query)
        }
        if (this.let { intent.resolveActivity(it.packageManager) } != null) {
            startActivity(intent)
        }
    }
}