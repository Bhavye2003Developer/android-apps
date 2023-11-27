package com.example.intellicia

import android.content.Intent
import android.net.VpnService
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var packetTextView: TextView
    private val packetContentData = PacketContentData()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        packetTextView = findViewById(R.id.packetText)
        packetTextView.text = packetContentData.content

        startVpnService()
    }

    private fun startVpnService() {
        val vpnIntent = VpnService.prepare(this)
        if (vpnIntent != null) {
            startActivityForResult(vpnIntent, 0)
        } else {
            onActivityResult(0, RESULT_OK, null)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            val serviceIntent = Intent(this, NetworkCapture::class.java)
            // Pass packetContentData as an extra to the service
            serviceIntent.putExtra("packetContentData", packetContentData)
            startService(serviceIntent)
        }
    }
}