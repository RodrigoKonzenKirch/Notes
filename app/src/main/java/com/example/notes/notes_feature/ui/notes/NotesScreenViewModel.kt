package com.example.notes.notes_feature.ui.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.di.DispatcherIo
import com.example.notes.notes_feature.aplication.DeleteNoteUseCase
import com.example.notes.notes_feature.aplication.GetAllNotesUseCase
import com.example.notes.notes_feature.data.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesScreenViewModel @Inject constructor(
    private val getNotesUseCase: GetAllNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    @DispatcherIo private val dispatcherIo: CoroutineDispatcher
 ): ViewModel() {

    private var _state: StateFlow<List<Note>> = getNotesUseCase()
        .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList())
    val state: StateFlow<List<Note>> = _state

    fun deleteNote(note: Note){
        viewModelScope.launch(dispatcherIo) {
            deleteNoteUseCase(note)
        }
    }

}
