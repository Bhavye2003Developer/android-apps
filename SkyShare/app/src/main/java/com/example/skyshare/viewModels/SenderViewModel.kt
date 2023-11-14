package com.example.skyshare.viewModels

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.IntentFilter
import android.net.wifi.WpsInfo
import android.net.wifi.p2p.WifiP2pConfig
import android.net.wifi.p2p.WifiP2pDevice
import android.net.wifi.p2p.WifiP2pManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.skyshare.MainActivity
import com.example.skyshare.wifiDirect.WiFiDirectBroadcastReceiver

class SenderViewModel(private val application: Application) : AndroidViewModel(application) {
    val wifip2pEnabled: MutableLiveData<Boolean> = MutableLiveData(false)

    private var intentFilter: IntentFilter = IntentFilter()
    private var channel: WifiP2pManager.Channel
    private var manager: WifiP2pManager

    private var _peersList: MutableLiveData<MutableList<WifiP2pDevice>> = MutableLiveData(
        mutableListOf()
    )
    val peersList: LiveData<MutableList<WifiP2pDevice>>
        get() = _peersList
    private val peers = mutableListOf<WifiP2pDevice>()


    private lateinit var receiver: WiFiDirectBroadcastReceiver

    private val peerListListener = WifiP2pManager.PeerListListener { peerList ->
        val refreshedPeers = peerList.deviceList
        if (refreshedPeers != peers) {
            peers.clear()
            peers.addAll(refreshedPeers)
            // If an AdapterView is backed by this data, notify it
            // of the change. For instance, if you have a ListView of
            // available peers, trigger an update.
            // Perform any other updates needed based on the new list of
            // peers connected to the Wi-Fi P2P network.
            _peersList.value = peers
        }
    }

    init {
        // Indicates a change in the Wi-Fi Direct status.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION)

        // Indicates a change in the list of available peers.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION)

        // Indicates the state of Wi-Fi Direct connectivity has changed.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION)

        // Indicates this device's details have changed.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION)

        manager = application.getSystemService(Context.WIFI_P2P_SERVICE) as WifiP2pManager
        channel = manager.initialize(application.applicationContext, application.mainLooper, null)
    }
    fun registerReceiver(){
        // register the receiver
        receiver = WiFiDirectBroadcastReceiver(manager, channel, MainActivity(), ::isWifip2pEnabled, peerListListener)
        application.registerReceiver(receiver, intentFilter)
    }
    fun unregisterReceiver(){
        application.unregisterReceiver(receiver)
    }
    private fun isWifip2pEnabled(isEnabled: Boolean){
        wifip2pEnabled.value = isEnabled
    }

    @SuppressLint("MissingPermission")
    fun initiatePeerDiscovery(){
        manager.discoverPeers(channel, object : WifiP2pManager.ActionListener {
            override fun onSuccess() {
                // Code for when the discovery initiation is successful goes here.
                // No services have actually been discovered yet, so this method
                // can often be left blank. Code for peer discovery goes in the
                // onReceive method, detailed below.

                // peer discovery successfully initiated
                Log.d("testing", "peer discovery successfully initiated")
            }
            override fun onFailure(reasonCode: Int) {
                // Code for when the discovery initiation fails goes here.
                // Alert the user that something went wrong.
                Log.d("testing", "peer discovery failed")
            }
        })
    }

    @SuppressLint("MissingPermission")
    fun connectDevice(device: WifiP2pDevice){
        val config = WifiP2pConfig().apply {
            deviceAddress = device.deviceAddress
            wps.setup = WpsInfo.PBC
        }

        manager.connect(channel, config, object : WifiP2pManager.ActionListener {

            override fun onSuccess() {
                // WiFiDirectBroadcastReceiver notifies us. Ignore for now.
                Log.d("testing", "connected successfully")
            }

            override fun onFailure(reason: Int) {
                Log.d("testing", "Connect failed. Retry.")
            }
        })
    }
}

@Suppress("UNCHECKED_CAST")
class SenderViewModelFactory(private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SenderViewModel::class.java)) {
            SenderViewModel(application) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}