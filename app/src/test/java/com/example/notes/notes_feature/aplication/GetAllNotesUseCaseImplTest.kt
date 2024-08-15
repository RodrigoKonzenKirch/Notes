package com.example.notes.notes_feature.aplication

import com.example.notes.notes_feature.data.Note
import com.example.notes.notes_feature.domain.NotesRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetAllNotesUseCaseImplTest {

    private val notesRepository = mockk<NotesRepository>(relaxed = true)
    private val getAllNotesUseCase = GetAllNotesUseCaseImpl(notesRepository)

    @Test
    fun `should call the repository`() {
        getAllNotesUseCase()

        verify { notesRepository.getAllNotes() }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should return the correct value`() = runTest {
        val note1 = Note(1, "note1", "content1", 1)
        val note2 = Note(2, "note2", "content2", 2)
        val expectedValue = listOf(note1, note2)

        every { notesRepository.getAllNotes() } returns flow{ emit(expectedValue) }
        val result = getAllNotesUseCase().first()

        assert(result == expectedValue)
    }
}