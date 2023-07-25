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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notes.notes_feature.data.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(viewModel: NotesScreenViewModel = viewModel(), modifier: Modifier) {
    val title = remember {
        mutableStateOf("Notes")
    }
    val notesState = viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(title.value) })
        },
        floatingActionButton = {
            // TODO: 1. Implement screen that add notes
            // TODO: 2. Add fixed set of colors for notes
            FloatingActionButton(onClick = { viewModel.createNote(Note(0,"Empty note","Content for testing. Simply write something here.\nMore lines to see how it looks like\nMore lines to see how it looks like \nMore lines to see how it looks like\nOk, That's good enough I think", Color.Gray.toArgb())) }) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Add note"
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
fun NotesList(notes: List<Note>, onDeleteNote: (note: Note) -> Unit, modifier: Modifier) {

    LazyColumn(modifier){
        items(notes){ note ->
            Note(note, onDeleteNote)
        }
    }
}

@Composable
fun Note(note: Note, onDeleteNote: (note: Note) -> Unit){
    Box(
        contentAlignment = Alignment.BottomEnd,
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
            contentDescription = "Delete Note",
            modifier = Modifier
                .padding(4.dp)
                .clickable {
                    onDeleteNote(note)
                },
            tint = Color.Black

        )
    }

}
