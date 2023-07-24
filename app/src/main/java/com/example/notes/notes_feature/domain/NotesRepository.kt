package com.example.notes.notes_feature.domain

import com.example.notes.notes_feature.data.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    suspend fun insertNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)

    suspend fun getNoteById(id: Int): Flow<Note>

    suspend fun getAllNotes(): Flow<List<Note>>
}