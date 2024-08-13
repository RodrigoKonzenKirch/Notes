package com.example.notes.notes_feature.aplication

import com.example.notes.notes_feature.domain.NotesRepository
import javax.inject.Inject

class GetNoteUseCaseImpl @Inject constructor(
    private val notesRepository: NotesRepository
): GetNoteUseCase {
    override fun invoke(id: Int) = notesRepository.getNoteById(id)
}