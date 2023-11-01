package com.example.nasafetcher.adapters

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nasafetcher.R
import com.example.nasafetcher.network.spaceNews.News

class SpaceNews(private val context: Context, private val newsList: ArrayList<News>) :
    RecyclerView.Adapter<SpaceNews.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val summary: TextView = view.findViewById(R.id.summary)
        val publishedAt: TextView = view.findViewById(R.id.publishedAt)
        val newsImage: ImageView = view.findViewById(R.id.newsImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.space_news_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = newsList[position]
        holder.title.text = item.title
        holder.summary.text = item.summary
        holder.publishedAt.text = item.publishedAt
        Glide.with(context).load(item.imageUrl).into(holder.newsImage)

        holder.title.setOnClickListener {
            searchWeb(item.url)
        }
    }

    private fun searchWeb(query: String) {
        val intent = Intent(Intent.ACTION_WEB_SEARCH).apply {
            putExtra(SearchManager.QUERY, query)
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

}