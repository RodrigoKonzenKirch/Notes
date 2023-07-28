package com.example.notes.notes_feature.ui.add_edit_notes

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.notes.R
import com.example.notes.notes_feature.ui.NoteColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditNotesScreen(
    modifier: Modifier,
    onBack: () -> Unit,
) {
    val viewModel = hiltViewModel<AddEditNotesViewModel>()
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick =  onBack ) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.fab_back_content_description)
                        )
                    }
                },
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = viewModel::saveNote ) {
                Icon(
                    Icons.Filled.Done,
                    contentDescription = stringResource(R.string.fab_done_content_description)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->

        val message = stringResource(R.string.snackbar_note_saved)
        LaunchedEffect(key1 = state.value.shouldShowMessage) {
            if (state.value.shouldShowMessage) {
                snackbarHostState.showSnackbar(message)
                viewModel.messageShown()
            }
        }
        Column(Modifier.padding(innerPadding)) {
            ColorsRow(
                onSelectColor = { color ->
                    viewModel.updateColor(color)},
                selectedColor = Color(state.value.color)
            )

            Note(
                state.value.title,
                state.value.content,
                onTitleChanged = viewModel::updateTitle,
                onContentChanged = viewModel::updateContent
            )
        }

    }

}

@Composable
fun ColorsRow(
    selectedColor: Color,
    onSelectColor: (Color) -> Unit
) {
    val availableColorsList = mutableListOf<Color>()
    for (color in NoteColors.values()){
        availableColorsList.add(color.color)
    }

    Row(modifier = Modifier
        .selectableGroup()
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly) {
        availableColorsList.forEachIndexed { index, radioColor ->
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .selectable(
                        selected = (selectedColor == radioColor),
                        onClick = {
                            onSelectColor(radioColor)
                        },
                        role = Role.RadioButton
                    )
            ) {
                Canvas(
                    modifier = Modifier
                        .size(size = 60.dp)
                        .border(
                            color = if (selectedColor == radioColor) Color.Cyan else Color.Transparent,
                            width = 2.dp
                        )
                ) {
                    drawRoundRect(availableColorsList[index])
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Note(
    title: String,
    content: String,
    onTitleChanged: (String) -> Unit,
    onContentChanged: (String) -> Unit,
) {
    Column {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = title,
            singleLine = true,
            label = { Text(text = stringResource(R.string.textfield_label_note_title)) },
            onValueChange = { newText ->
                onTitleChanged(newText)
            },
        )

        TextField(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            value = content,
            label = { Text(text = stringResource(R.string.textfield_label_note_content)) },
            onValueChange = { newText ->
                onContentChanged(newText)
            },
        )
    }
}