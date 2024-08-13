package com.example.notes.notes_feature.aplication

import com.example.notes.notes_feature.domain.NotesRepository
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class GetAllNotesUseCaseImplTest {

    private val notesRepository = mockk<NotesRepository>(relaxed = true)
    private val getAllNotesUseCase = GetAllNotesUseCaseImpl(notesRepository)

    @Test
    fun `should call the repository`() {
        getAllNotesUseCase()

        verify { notesRepository.getAllNotes() }
    }

}