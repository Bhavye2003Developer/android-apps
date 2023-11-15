package com.example.youtubedownloader

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class YTViewAdapter(private val context: Context,private val ytVideos: List<String>) : RecyclerView.Adapter<YTViewAdapter.ItemViewHolder>() {

    private val path = "/storage/emulated/0/yt_downloads/"
    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ytTitle: TextView = view.findViewById(R.id.yt_video_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.yt_list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int = ytVideos.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val video = ytVideos[position]
        holder.ytTitle.text = video.split(path)[1]

        holder.ytTitle.setOnClickListener {
            openFile(video)
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    fun openFile(videoPath: String){
        try{
            val uri = Uri.parse(videoPath)
            val intent = Intent(Intent.ACTION_VIEW)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.setDataAndType(uri, "video/*")
            context.startActivity(intent)
        }
        catch (_: Exception){
            Toast.makeText(context, "Error occurred!", Toast.LENGTH_SHORT).show()
        }
    }
}