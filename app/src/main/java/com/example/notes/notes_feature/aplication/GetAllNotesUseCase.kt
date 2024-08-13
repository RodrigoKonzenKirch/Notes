package com.example.notes.notes_feature.aplication

import com.example.notes.notes_feature.data.Note
import kotlinx.coroutines.flow.Flow

interface GetAllNotesUseCase {
    operator fun invoke(): Flow<List<Note>>
}