package com.example.notes.notes_feature.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int,

    val title: String,
    val content: String,
    val color: Int,
)