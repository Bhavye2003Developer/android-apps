package com.example.gptopenai.chatRecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gptopenai.R
import com.example.gptopenai.gptViewModel.Chat

class ChatRecyclerViewAdapter(private val chats: MutableList<Chat>) :
    RecyclerView.Adapter<ChatRecyclerViewAdapter.ChatViewHolder>() {

    class ChatViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val queryView: TextView = view.findViewById(R.id.questionTextView)
        val answerView: TextView = view.findViewById(R.id.answerTextView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.chat_item, parent, false)
        return ChatViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        return chats.size
    }
    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val singleChat = chats[position]
        holder.queryView.text = singleChat.userQuery
        holder.answerView.text = singleChat.answer
    }

}