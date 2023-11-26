package com.example.guardia

import android.content.Context
import android.content.pm.ApplicationInfo
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class UidRecyclerViewAdapter(
    private val context: Context,
    private val packages: List<ApplicationInfo>,
    private val getNetworkStatsUid: (uid: Int) -> Unit
) : RecyclerView.Adapter<UidRecyclerViewAdapter.ItemViewHolder>() {
    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.packageUidText)
        val appIcon: ImageView = view.findViewById(R.id.appIcon)
        val packageItemLayout: LinearLayout = view.findViewById(R.id.packageItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int = packages.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val packageItem = packages[position]
        holder.textView.text = packageItem.packageName

        val icon: Drawable =
            context.packageManager.getApplicationIcon(packageItem.packageName)
        holder.appIcon.setImageDrawable(icon)

        holder.packageItemLayout.setOnClickListener {
//            val launchIntent: Intent =
//                context.packageManager.getLaunchIntentForPackage(packageItem.packageName)!!
//            context.startActivity(launchIntent)
            getNetworkStatsUid(packageItem.uid)
        }

    }
}