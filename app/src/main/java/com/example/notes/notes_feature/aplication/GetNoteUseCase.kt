package com.example.notes.notes_feature.aplication

import com.example.notes.notes_feature.data.Note

interface GetNoteUseCase {
    operator fun invoke(id: Int): Note
}