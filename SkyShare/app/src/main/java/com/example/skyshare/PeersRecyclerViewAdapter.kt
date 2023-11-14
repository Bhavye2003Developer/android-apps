package com.example.skyshare

import android.annotation.SuppressLint
import android.net.wifi.p2p.WifiP2pDevice
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PeersRecyclerViewAdapter(private val peersList: MutableList<WifiP2pDevice>, private val connectDevice: (device: WifiP2pDevice) -> Unit) :
    RecyclerView.Adapter<PeersRecyclerViewAdapter.ItemViewHolder>() {
    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val wifiDevice: TextView = view.findViewById(R.id.wifiDevice)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.peers_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }
    override fun getItemCount(): Int {
        return peersList.size
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val wifiDevice = peersList[position]
        holder.wifiDevice.text = "${position+1}. ${wifiDevice.deviceName}"

        holder.wifiDevice.setOnClickListener {
            connectDevice(wifiDevice)
        }
    }
}