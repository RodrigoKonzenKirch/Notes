package com.example.notes.notes_feature.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.notes_feature.data.Note
import com.example.notes.notes_feature.domain.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesScreenViewModel @Inject constructor(
    private val repository: NotesRepository
): ViewModel() {
    private val dispatcher = Dispatchers.IO
    private var _state: StateFlow<List<Note>> = repository.getAllNotes()
        .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList())
    val state: StateFlow<List<Note>> = _state

    fun createNote(note: Note){
        viewModelScope.launch(dispatcher) {
            repository.insertNote(note)
        }
    }

    fun deleteNote(note: Note){
        viewModelScope.launch(dispatcher) {
            repository.deleteNote(note)
        }
    }

}
