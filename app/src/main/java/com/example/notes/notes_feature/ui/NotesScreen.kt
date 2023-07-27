package com.example.notes.notes_feature.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notes.R
import com.example.notes.notes_feature.data.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(
    onAddNote: (noteId: Int) -> Unit,
    modifier: Modifier,
) {
    val viewModel = hiltViewModel<NotesScreenViewModel>()
    val notesState = viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.notes_screen_title)) },
                Modifier.padding(horizontal = 16.dp)
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onAddNote(0) }) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = stringResource(R.string.fab_add_note_content_description)
                )
            }
        },
    ) { paddingValues ->
        Column(Modifier.padding(paddingValues)) {
            NotesList(
                notesState.value,
                onDeleteNote = {
                    viewModel.deleteNote(it)
                },
                modifier = modifier
            )
        }
    }
}

@Composable
fun NotesList(
    notes: List<Note>,
    onDeleteNote: (note: Note) -> Unit,
    modifier: Modifier,
) {

    LazyColumn(modifier){
        items(notes){ note ->
            Note(note, onDeleteNote)
        }
    }
}

@Composable
fun Note(
    note: Note,
    onDeleteNote: (note: Note) -> Unit,
){
    Box(
        contentAlignment = Alignment.TopEnd,
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .background(
                Brush.horizontalGradient(listOf(Color.White, Color(note.color))),
                RoundedCornerShape(10.dp)
            )
    ) {
        Column(Modifier.padding(12.dp)) {
            Text(
                text = note.title+" "+note.id,

                Modifier
                    .fillMaxWidth(),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = note.content,
                Modifier
                    .fillMaxWidth(),
                color = Color.Black,
                fontFamily = FontFamily.Serif

            )

        }
        Icon(
            Icons.Filled.Delete,
            contentDescription = stringResource(R.string.icon_delete_note_content_description),
            modifier = Modifier
                .padding(4.dp)
                .clickable {
                    onDeleteNote(note)
                },
            tint = Color.Black

        )
    }

}
