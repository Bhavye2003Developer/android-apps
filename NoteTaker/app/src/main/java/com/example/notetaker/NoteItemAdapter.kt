package com.example.notetaker

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notetaker.noteRoom.Note

class NoteItemAdapter(
    private val notes: List<Note>,
    private val tickOnClick: (position: Int) -> Unit,
    private val removeNoteOnClick: (note: Note) -> Unit
) :
    RecyclerView.Adapter<NoteItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val noteText: TextView = view.findViewById(R.id.noteText)
        val tickNote: ImageView = view.findViewById(R.id.tick_symbol)
        val removeNote: ImageView = view.findViewById(R.id.wrong_symbol)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = notes[position]
        holder.noteText.text = item.note

        holder.tickNote.setOnClickListener {
            tickOnClick(notes[position].id)
        }
        holder.removeNote.setOnClickListener {
            removeNoteOnClick(notes[position])
        }
        if (notes[position].isCompleted == 1) {
            holder.noteText.paintFlags = holder.noteText.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
    }
}