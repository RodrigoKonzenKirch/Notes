package com.example.notes.notes_feature.ui.add_edit_notes

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import com.example.notes.MainCoroutineRule
import com.example.notes.NotesDestinationsArgs
import com.example.notes.notes_feature.aplication.GetNoteUseCase
import com.example.notes.notes_feature.aplication.UpsertNoteUseCase
import com.example.notes.notes_feature.domain.NotesRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.example.notes.notes_feature.data.Note
import io.mockk.coVerify
import kotlinx.coroutines.test.runTest

@OptIn(ExperimentalCoroutinesApi::class)
class AddEditNotesViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val upsertNoteUseCaseMockk = mockk<UpsertNoteUseCase>(relaxed = true)
    private val getNoteUseCaseMockk = mockk<GetNoteUseCase>(relaxed = true)

    private val myTestScheduler = TestCoroutineScheduler()
    private val testDispatcher = UnconfinedTestDispatcher(myTestScheduler)

    private lateinit var viewModel: AddEditNotesViewModel

    @Before
    fun setup() {
        viewModel = AddEditNotesViewModel(upsertNoteUseCaseMockk, getNoteUseCaseMockk , SavedStateHandle(mapOf(NotesDestinationsArgs.NOTE_ID_ARG to "0")), testDispatcher)
    }

    @Test
    fun `viewModel initialized with argument 0, state should have default values`() {

        assertThat(viewModel.uiState.value).isEqualTo(AddEditNoteUiState())
    }

    @Test
    fun `viewModel initialized with argument 1, fetchNote is called with given argument`() {

        // When the viewModel is initialized with destinationArgs argument
        val viewModelWithArg = AddEditNotesViewModel(
            upsertNoteUseCaseMockk,
            getNoteUseCaseMockk,
            SavedStateHandle(mapOf(NotesDestinationsArgs.NOTE_ID_ARG to "1")),
            testDispatcher
        )

        // Repository should fetch the note with the parameters id
        verify { getNoteUseCaseMockk(1) }
    }

    @Test
    fun `viewModel initialized with argument 1, state is updated with content of note id 1`() = runTest {

        val expectedResult = AddEditNoteUiState(1, "title1", "content1", 1)
        every { getNoteUseCaseMockk(1) } returns Note(1, "title1", "content1", 1)
        val viewModelWithArg = AddEditNotesViewModel(
            upsertNoteUseCaseMockk,
            getNoteUseCaseMockk,
            SavedStateHandle(mapOf(NotesDestinationsArgs.NOTE_ID_ARG to "1")),
            testDispatcher
        )

        assertThat(viewModelWithArg.uiState.value).isEqualTo(expectedResult)
    }

    @Test
    fun `updateTitle should update state's title value`() {

        val title = "Title"
        viewModel.updateTitle(title)

        assertThat(viewModel.uiState.value.title).isEqualTo(title)
    }

    @Test
    fun `updateContent should update state's content value`() {

        val content = "Content"
        viewModel.updateContent(content)

        assertThat(viewModel.uiState.value.content).isEqualTo(content)
    }

    @Test
    fun `updateColor should update state's color value`() {

        val color = Color(1)
        viewModel.updateColor(color)

        assertThat(viewModel.uiState.value.color).isEqualTo(1)
    }


    @Test
    fun `saveNote, calls repository upsert with correct argument`(){

        val note = Note(1, "title1", "content1", 1)
        every { getNoteUseCaseMockk(1) } returns note
        val viewModelWithArg = AddEditNotesViewModel(
            upsertNoteUseCaseMockk,
            getNoteUseCaseMockk,
            SavedStateHandle(mapOf(NotesDestinationsArgs.NOTE_ID_ARG to "1")),
            testDispatcher
        )
        viewModelWithArg.saveNote()

        coVerify { upsertNoteUseCaseMockk(note) }

    }

    @Test
    fun `messageShown called, value of uiState shouldShowMessage attribute should be false`() {

        val note = Note(1, "title1", "content1", 1)
        every { getNoteUseCaseMockk(1) } returns note
        val viewModelWithArg = AddEditNotesViewModel(
            upsertNoteUseCaseMockk,
            getNoteUseCaseMockk,
            SavedStateHandle(mapOf(NotesDestinationsArgs.NOTE_ID_ARG to "1")),
            testDispatcher
        )
        //  when saveNote is called, shouldShowMessage is set to true
        viewModelWithArg.saveNote()
        assertThat(viewModelWithArg.uiState.value.shouldShowMessage).isTrue()

        //  then calling messageShown should set shouldShowMessage to false
        viewModelWithArg.messageShown()
        assertThat(viewModelWithArg.uiState.value.shouldShowMessage).isFalse()
    }

    @Test
    fun `saveNote is called, uiState should be AddEditNoteUiState's default values with shouldShowMessage true`() {

        val note = Note(1, "title1", "content1", 1)
        val expectedValue = AddEditNoteUiState(shouldShowMessage = true)

        every { getNoteUseCaseMockk(1) } returns note
        val viewModelWithArg = AddEditNotesViewModel(
            upsertNoteUseCaseMockk,
            getNoteUseCaseMockk,
            SavedStateHandle(mapOf(NotesDestinationsArgs.NOTE_ID_ARG to "1")),
            testDispatcher
        )
        viewModelWithArg.saveNote()

        assertThat(viewModelWithArg.uiState.value).isEqualTo(expectedValue)
    }
}