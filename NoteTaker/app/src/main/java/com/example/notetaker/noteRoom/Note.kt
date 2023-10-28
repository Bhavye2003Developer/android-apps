package com.example.notetaker.noteRoom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NoteTable")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "note")
    val note: String,

    @ColumnInfo(name = "isCompleted")
    val isCompleted: Int = 0
)