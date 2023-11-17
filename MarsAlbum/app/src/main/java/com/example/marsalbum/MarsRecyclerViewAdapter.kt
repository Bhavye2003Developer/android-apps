package com.example.marsalbum

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MarsRecyclerViewAdapter(private val context: Context,private val marsItems: MarsProperty) : RecyclerView.Adapter<MarsRecyclerViewAdapter.ItemViewHolder>() {
    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val marsImage: ImageView = view.findViewById(R.id.marsImage)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        val layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT, TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 230f, parent.resources.displayMetrics
            ).toInt()
        )
        layoutParams.rightMargin = 0
        layoutParams.leftMargin = 0
        layoutParams.topMargin = 0
        layoutParams.bottomMargin = 0
        adapterLayout.layoutParams = layoutParams
        return ItemViewHolder(adapterLayout)
    }
    override fun getItemCount(): Int = marsItems.size
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = marsItems[position]
        Glide.with(context).load(item.img_src).apply(RequestOptions().override(200, 200)).into(holder.marsImage)
    }
}