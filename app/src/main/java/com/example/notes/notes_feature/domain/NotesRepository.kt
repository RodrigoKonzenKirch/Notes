package com.example.notes.notes_feature.domain

import com.example.notes.notes_feature.data.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    suspend fun insertNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun upsertNote(note: Note)

    suspend fun deleteNote(note: Note)

    fun getNoteById(id: Int): Note

    fun getAllNotes(): Flow<List<Note>>
}