package com.example.netscan

import android.content.Context
import android.net.wifi.WifiManager
import android.text.format.Formatter
import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.InetAddress
import java.net.UnknownHostException


class DevicesConnectedToNetwork(private val context: Context) {
    private val TAG = "testing"
    fun getDevices() {
        val hosts = getDeviceList()
        Log.d(TAG, hosts)
    }

    private fun getDeviceList(): String {
        val deviceList = StringBuilder()

        // Get the local IP address
        val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager?
        val wifiInfo = wifiManager!!.connectionInfo
        val ipAddress = wifiInfo.ipAddress
        val ipAddressString: String = Formatter.formatIpAddress(ipAddress)

        // Get the network prefix (e.g., "192.168.1.")
        val ipAddressParts = ipAddressString.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()
        val networkPrefix =
            ipAddressParts[0] + "." + ipAddressParts[1] + "." + ipAddressParts[2] + "."

        // Use ARP to get the list of devices on the network
        try {
            val process = Runtime.getRuntime().exec("/system/bin/ip neigh show")
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                val parts = line?.split("\\s+".toRegex())?.dropLastWhile { it.isEmpty() }
                    ?.toTypedArray()
                if (parts!!.size >= 3 && parts[0] == networkPrefix && parts[1] != "FAILED") {
                    val deviceIP = parts[0] + parts[2]
                    val deviceHostname = getHostName(deviceIP)
                    deviceList.append("Device IP: ").append(deviceIP).append(", Hostname: ")
                        .append(deviceHostname).append("\n")
                }
//                line?.let { Log.d(TAG, it) }
            }
        } catch (e: IOException) {
            Log.e("NetworkScanActivity", "Error executing ARP command", e)
        }

        return deviceList.toString()
    }

    private fun getHostName(ipAddress: String): String {
        return try {
            val inetAddress = InetAddress.getByName(ipAddress)
            inetAddress.hostName
        } catch (e: UnknownHostException) {
            Log.e("NetworkScanActivity", "Error getting host name", e)
            "Unknown"
        }
    }

}