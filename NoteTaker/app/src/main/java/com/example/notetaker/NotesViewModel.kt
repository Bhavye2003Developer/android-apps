package com.example.notetaker

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notetaker.noteRoom.Note
import com.example.notetaker.noteRoom.NoteDatabase


class NotesViewModel(context: Context) : ViewModel() {

    private var database: NoteDatabase
    var allNotes: LiveData<List<Note>>

    init {
        database = NoteDatabase.getDatabase(context)
        allNotes = database.NoteDao().getAllNotes()
    }

    @WorkerThread
    suspend fun insertNote(note: Note) {
        database.NoteDao().addNote(note)
    }

    suspend fun setNoteCompleted(noteId: Int) {
        database.NoteDao().setNoteCompleted(noteId)
    }

    suspend fun removeNote(note: Note) {
        database.NoteDao().removeNote(note)
    }
}

@Suppress("UNCHECKED_CAST")
class NoteViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NotesViewModel(context = context) as T
    }
}