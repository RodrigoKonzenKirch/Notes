package com.example.notes.notes_feature.aplication

import com.example.notes.notes_feature.data.Note
import com.example.notes.notes_feature.domain.NotesRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

class UpsertNoteUseCaseImplTest{

    private val notesRepository = mockk<NotesRepository>(relaxed = true)
    private val useCase = UpsertNoteUseCaseImpl(notesRepository)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `UpsetNote should call repository`() = runTest{
        val note = Note(1, "title", "content", 1)

        useCase(note)

        coVerify { notesRepository.upsertNote(note) }
    }
}