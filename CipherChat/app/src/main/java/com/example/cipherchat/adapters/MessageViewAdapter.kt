package com.example.cipherchat.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cipherchat.R
import com.example.cipherchat.utils.Message
import java.text.Format
import java.text.SimpleDateFormat
import java.util.Date

class MessageViewAdapter(private val chats: MutableList<Message>): RecyclerView.Adapter<MessageViewAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textMessage: TextView = view.findViewById(R.id.textMessage)
        val textSender: TextView = view.findViewById(R.id.textSender)
        val textTime: TextView = view.findViewById(R.id.time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.message_layout, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int = chats.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val message = chats[position]
        holder.textMessage.text = message.text
        holder.textSender.text = message.sender
        holder.textTime.text = convertTime(message.sendTime)
    }

    @SuppressLint("SimpleDateFormat")
    fun convertTime(time: Long): String? {
        val date = Date(time)
        val format: Format = SimpleDateFormat("yyyy MM dd HH:mm:ss")
        return format.format(date)
    }
}