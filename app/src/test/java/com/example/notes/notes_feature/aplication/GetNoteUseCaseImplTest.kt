package com.example.notes.notes_feature.aplication

import com.example.notes.notes_feature.domain.NotesRepository
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class GetNoteUseCaseImplTest{

    private val notesRepository = mockk<NotesRepository>(relaxed = true)
    val getNoteUseCase = GetNoteUseCaseImpl(notesRepository)

    @Test
    fun `get note should call repository`(){
        val id = 1

        getNoteUseCase(id)

        verify { notesRepository.getNoteById(id) }
    }

}