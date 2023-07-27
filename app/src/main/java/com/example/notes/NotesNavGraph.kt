package com.example.notes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.notes.notes_feature.ui.add_edit_notes.AddEditNotesScreen
import com.example.notes.notes_feature.ui.notes.NotesScreen

@Composable
fun NotesNavGraph(
    modifier: Modifier,
    navController: NavHostController,
    startDestination: String = NotesDestinations.NOTES_ROUTE,
    navActions: NotesNavigationActions = remember(navController) {
        NotesNavigationActions(navController)
    }
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(NotesDestinations.NOTES_ROUTE) {
            NotesScreen(
                modifier = modifier,
                onAddNote = { noteId -> navActions.navigateToAddEditNote(noteId) }
                )
        }

        composable(NotesDestinations.ADD_EDIT_NOTES_ROUTE) {
            AddEditNotesScreen(
                modifier = modifier,
                onBack = { navController.popBackStack() }
            )
        }
    }


}