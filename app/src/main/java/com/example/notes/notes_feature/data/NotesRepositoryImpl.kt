package com.example.notes.notes_feature.data

import com.example.notes.notes_feature.domain.NotesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(
    private val notesDao: NotesDao,
) : NotesRepository {

    override suspend fun insertNote(note: Note) {
        notesDao.insertNote(note)
    }

    override suspend fun updateNote(note: Note) {
        notesDao.updateNote(note)
    }

    override suspend fun upsertNote(note: Note) {
        notesDao.upsertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        notesDao.deleteNote(note)
    }

    override fun getNoteById(id: Int): Note {
        return notesDao.getNoteById(id)
    }

    override  fun getAllNotes(): Flow<List<Note>> {
        return notesDao.getAllNotes()
    }
}