package com.example.notes.notes_feature.aplication

import com.example.notes.notes_feature.data.Note
import com.example.notes.notes_feature.domain.NotesRepository
import javax.inject.Inject

class InsertNoteUseCaseImpl @Inject constructor(
    private val repository: NotesRepository
): InsertNoteUseCase {

    override suspend operator fun invoke(note: Note) {
        repository.insertNote(note)
    }
}
