package com.example.notes.notes_feature.aplication

import com.example.notes.notes_feature.data.Note
import com.example.notes.notes_feature.domain.NotesRepository
import javax.inject.Inject

class DeleteNoteUseCaseImpl @Inject constructor(
    private val notesRepository: NotesRepository
): DeleteNoteUseCase {

    override suspend fun invoke(note: Note) {
        notesRepository.deleteNote(note)
    }

}