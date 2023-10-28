package com.example.revision

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.revision.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
// DATABINDING ADDED
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        setContentView(R.layout.activity_main)

        setSupportActionBar(binding.appBar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){
        R.id.about -> {
            Toast.makeText(this, "Showing About Me ...", Toast.LENGTH_SHORT).show()
            val intent: Intent = Intent(this, AboutMeActivity::class.java)
            startActivity(intent)
            true
        }
        else -> super.onOptionsItemSelected(item)

    }
}