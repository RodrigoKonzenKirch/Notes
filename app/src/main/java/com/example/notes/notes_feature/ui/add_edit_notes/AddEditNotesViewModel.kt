package com.example.notes.notes_feature.ui.add_edit_notes

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.NotesDestinationsArgs
import com.example.notes.notes_feature.data.Note
import com.example.notes.notes_feature.domain.NotesRepository
import com.example.notes.notes_feature.ui.NoteColors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This color is to be used as default color
 * for new notes **/
object DefaultColor{
    val COLOR: Int = NoteColors.BLUE.color.toArgb()
}

data class AddEditNoteUiState(
    val id: Int = 0,
    val title: String = "",
    val content: String = "",
    val color: Int = DefaultColor.COLOR,
    val isLoading: Boolean = false,
    val shouldShowMessage: Boolean = false,
)

@HiltViewModel
class AddEditNotesViewModel @Inject constructor(
    private val repository: NotesRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val noteId: String? = savedStateHandle[NotesDestinationsArgs.NOTE_ID_ARG]
    private val dispatcher = Dispatchers.IO

    private val _uiState = MutableStateFlow(AddEditNoteUiState())
    val uiState: StateFlow<AddEditNoteUiState> = _uiState.asStateFlow()

    init {
        val noteIdInt = noteId?.toInt() ?: 0
        if (noteIdInt  != 0) {
            loadNote(noteIdInt)
        }


    }

    fun updateTitle(newTitle: String) {
        _uiState.update {
            it.copy(title = newTitle)
        }
    }

    fun updateContent(newContent: String) {
        _uiState.update {
            it.copy(content = newContent)
        }
    }

    fun updateColor(newColor: Color) {
        _uiState.update {
            it.copy(color = newColor.toArgb())
        }
    }
    fun saveNote() {
        val note = Note(
            _uiState.value.id,
            _uiState.value.title,
            _uiState.value.content,
            _uiState.value.color
        )
        viewModelScope.launch(dispatcher) {
            repository.upsertNote(note)
        }
        _uiState.update {
            it.copy(shouldShowMessage = true)
        }
        clearCurrentNote()
    }

    fun messageShown() {
        _uiState.update {
            it.copy(shouldShowMessage = false)
        }
    }

    private fun clearCurrentNote(){
        _uiState.update {
            it.copy(
                id = 0,
                title = "",
                content = "",
                color = DefaultColor.COLOR
            )
        }
    }

    private fun loadNote(noteId: Int) {
        _uiState.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch(dispatcher) {
            repository.getNoteById(noteId).let { note ->
                _uiState.update {
                    it.copy(
                        id = note.id,
                        title = note.title,
                        content = note.content,
                        color = note.color,
                        isLoading = false
                    )
                }
            }
        }
    }
}

