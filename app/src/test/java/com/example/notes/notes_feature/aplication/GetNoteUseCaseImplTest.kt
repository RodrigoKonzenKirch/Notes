package com.example.notes.notes_feature.aplication

import com.example.notes.notes_feature.data.Note
import com.example.notes.notes_feature.domain.NotesRepository
import io.mockk.every
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

    @Test
    fun `get note should return note`() {
        val id = 1
        val expectedNote = Note(id, "title", "content", 1)

        every { notesRepository.getNoteById(id) } returns expectedNote

        val result = getNoteUseCase(id)
        assert(result == expectedNote)
    }

}