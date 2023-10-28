package com.example.notetaker.noteRoom

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {

    @Query("SELECT * FROM NoteTable")
    fun getAllNotes(): LiveData<List<Note>>

    @Insert
    fun addNote(note: Note)

    @Query("UPDATE NoteTable SET isCompleted = 1 WHERE id = :noteId")
    fun setNoteCompleted(noteId: Int)

    @Delete
    fun removeNote(note: Note)
}