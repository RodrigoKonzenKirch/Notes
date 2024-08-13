package com.example.notes.notes_feature.aplication

import com.example.notes.notes_feature.data.Note

interface UpsertNoteUseCase {
    suspend operator fun invoke(note: Note)
}