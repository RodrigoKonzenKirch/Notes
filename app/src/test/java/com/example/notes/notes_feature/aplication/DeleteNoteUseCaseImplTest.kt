package com.example.notes.notes_feature.aplication

import com.example.notes.notes_feature.data.Note
import com.example.notes.notes_feature.domain.NotesRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DeleteNoteUseCaseImplTest {

    private val notesRepository = mockk<NotesRepository>(relaxed = true)
    private val deleteNoteUseCase = DeleteNoteUseCaseImpl(notesRepository)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `deleteNote should call deleteNote on notesRepository`() = runTest {
        val note = Note(1, "Title", "Content", 1)

        deleteNoteUseCase(note)

        coVerify { notesRepository.deleteNote(note) }
    }

}