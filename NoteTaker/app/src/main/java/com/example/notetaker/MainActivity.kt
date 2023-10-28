package com.example.notetaker

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notetaker.databinding.ActivityLcBinding
import com.example.notetaker.noteRoom.Note
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLcBinding
    private lateinit var viewModel: NotesViewModel

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLcBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, NoteViewModelFactory(this))[NotesViewModel::class.java]

        binding.allNotes.setHasFixedSize(true)
        binding.allNotes.layoutManager = LinearLayoutManager(this)

        binding.btnAddNote.setOnClickListener {
            val noteText = binding.note.editableText.toString()
            binding.note.editableText.clear()

            Toast.makeText(this, "Note Added", Toast.LENGTH_SHORT).show()

            // adding a note
            GlobalScope.launch {
                viewModel.insertNote(Note(note = noteText))
            }
        }

        viewModel.allNotes.observe(this) {
            binding.allNotes.adapter = NoteItemAdapter(it, ::tickOnClick, ::removeNoteOnClick)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun tickOnClick(position: Int) {
        Toast.makeText(this, "Note Completed", Toast.LENGTH_SHORT).show()
        GlobalScope.launch {
            viewModel.setNoteCompleted(position)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun removeNoteOnClick(note: Note) {
        Toast.makeText(this, "Removed", Toast.LENGTH_SHORT).show()
        GlobalScope.launch {
            viewModel.removeNote(note)
        }
    }
}