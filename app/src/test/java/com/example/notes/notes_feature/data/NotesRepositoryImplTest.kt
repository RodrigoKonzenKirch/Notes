package com.example.notes.notes_feature.data

import io.mockk.coEvery
import io.mockk.coVerify
import org.junit.Test
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest

@OptIn(ExperimentalCoroutinesApi::class)
class NotesRepositoryImplTest {

    private var notesDao = mockk<NotesDao>(relaxed = true)
    private var notesRepositoryImpl = NotesRepositoryImpl(notesDao)

    @Test
    fun `insertNote should call notesDao insertNote`() = runTest {
        val note = Note(id = 1, title = "title", content = "content", color = 1)

        notesRepositoryImpl.insertNote(note)
        coVerify { notesDao.insertNote(note) }
    }

    @Test
    fun `updateNote should call NotesDao updateNote`() = runTest {
        val note = Note(id = 1, title = "title", content = "content", color = 1)

        notesRepositoryImpl.updateNote(note)
        coVerify { notesDao.updateNote(note) }
    }

    @Test
    fun `upsertNote should call NotesDao upsertNote`() = runTest {
        val note = Note(id = 1, title = "title", content = "content", color = 1)

        notesRepositoryImpl.upsertNote(note)
        coVerify { notesDao.upsertNote(note) }
    }

    @Test
    fun `deleteNote should call NotesDao deleteNote`() = runTest {
        val note = Note(id = 1, title = "title", content = "content", color = 1)

        notesRepositoryImpl.deleteNote(note)
        coVerify { notesDao.deleteNote(note) }
    }

    @Test
    fun `getNoteById should call NotesDao getNoteById`() = runTest {
        val id = 1

        notesRepositoryImpl.getNoteById(id)
        coVerify { notesDao.getNoteById(id) }
    }

    @Test
    fun `getAllNotes should return a list of notes`() = runTest {
        val expectedNotes = listOf(
            Note(id = 1, title = "title1", content = "content1", color = 1),
            Note(id = 2, title = "title2", content = "content2", color = 2)
        )

        coEvery { notesDao.getAllNotes() } returns flow { emit(expectedNotes) }

        val actualNotes = notesRepositoryImpl.getAllNotes().first()

        assert(actualNotes == expectedNotes)
        coVerify { notesDao.getAllNotes() }
    }
}