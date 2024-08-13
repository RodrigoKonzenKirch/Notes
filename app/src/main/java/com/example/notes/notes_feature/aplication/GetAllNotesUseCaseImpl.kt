package com.example.notes.notes_feature.aplication

import com.example.notes.notes_feature.domain.NotesRepository
import javax.inject.Inject

class GetAllNotesUseCaseImpl @Inject constructor(
    private val notesRepository: NotesRepository
): GetAllNotesUseCase {
    override operator fun invoke() = notesRepository.getAllNotes()
}