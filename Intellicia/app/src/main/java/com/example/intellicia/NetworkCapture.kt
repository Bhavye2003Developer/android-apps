package com.example.intellicia

import android.app.IntentService
import android.content.Intent
import android.net.VpnService
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.FileInputStream

@Suppress("DEPRECATION")
class NetworkCapture : VpnService() {

    private lateinit var job: Job
    private lateinit var packetContentData: PacketContentData

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        packetContentData = intent?.getParcelableExtra("packetContentData") ?: PacketContentData()
        if (intent != null) {
            val action = intent.action
            if ("ACTION_START" == action) {
                // Handle the start action
                Log.d("YourService", "Service started with ACTION_START")
            } else if ("ACTION_STOP" == action) {
                // Handle the stop action
                Log.d("YourService", "Service started with ACTION_STOP")
                stopSelf() // Stop the service when needed
            }
        }


        // start vpn connection
        val builder = Builder()
        val vpnInterface = builder
            .setSession("vpnSession")
            .addAddress("192.168.0.1",24)
            .addDnsServer("8.8.8.8")
            .addRoute("0.0.0.0",0)
            .establish()
        // Capture and analyze packets using vpnInterface

        job = CoroutineScope(Dispatchers.Default).launch {
            val `in` = FileInputStream(vpnInterface?.fileDescriptor)

            // Continuously read data from the VPN interface
            while (true) {
                val buffer = ByteArray(4096) // Adjust buffer size as needed
                val length = withContext(Dispatchers.IO) {
                    `in`.read(buffer)
                } // Read data from the VPN interface

                if (length > 0) {
                    // Process and log the captured packet data
                    processAndLogPacket(buffer, length)
                }
            }
        }

        return START_STICKY
    }

    private fun processAndLogPacket(buffer: ByteArray, length: Int) {
        val packetHex: String = bytesToHex(buffer, length)
        // Log or store the packet data as needed
        // Log or store the packet data as needed
        packetContentData.content = "Captured Packet: $packetHex"
    }

    private fun bytesToHex(bytes: ByteArray, length: Int): String {
        val sb = StringBuilder()
        for (i in 0 until length) {
            sb.append(String.format("%02X ", bytes[i]))
        }
        return sb.toString().trim { it <= ' ' }
    }

    override fun onRevoke() {
        super.onRevoke()
        // handle vpn revocation
        job.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}