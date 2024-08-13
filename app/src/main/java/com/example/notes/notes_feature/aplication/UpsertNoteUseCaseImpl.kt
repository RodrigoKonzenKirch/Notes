package com.example.notes.notes_feature.aplication

import com.example.notes.notes_feature.data.Note
import com.example.notes.notes_feature.domain.NotesRepository
import javax.inject.Inject

class UpsertNoteUseCaseImpl @Inject constructor(
    private val notesRepository: NotesRepository
): UpsertNoteUseCase  {
    override suspend operator fun invoke(note: Note) {
        notesRepository.upsertNote(note)
    }
}