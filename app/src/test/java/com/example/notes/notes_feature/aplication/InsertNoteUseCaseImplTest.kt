package com.example.notes.notes_feature.aplication

import com.example.notes.notes_feature.data.Note
import com.example.notes.notes_feature.domain.NotesRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class InsertNoteUseCaseImplTest {

    private val notesRepository = mockk<NotesRepository>(relaxed = true)
    private val insertNoteUseCase = InsertNoteUseCaseImpl(notesRepository)

    @Test
    fun `insertNoteUseCase should call insertNote on notesRepository`() = runTest {
        val note = Note(1, "Title", "Content", color = 1)

        insertNoteUseCase(note)

        coVerify { notesRepository.insertNote(note) }
    }
    
}