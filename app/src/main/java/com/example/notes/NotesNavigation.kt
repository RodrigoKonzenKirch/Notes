package com.example.notes

import androidx.navigation.NavController
import com.example.notes.NotesDestinations.ADD_EDIT_NOTES_ROUTE
import com.example.notes.NotesDestinations.NOTES_ROUTE
import com.example.notes.NotesDestinationsArgs.NOTE_ID_ARG
import com.example.notes.NotesScreens.ADD_EDIT_NOTES_SCREEN
import com.example.notes.NotesScreens.NOTES_SCREEN

private object NotesScreens{
    const val NOTES_SCREEN = "notes"
    const val ADD_EDIT_NOTES_SCREEN = "addEditNote"
}

object NotesDestinationsArgs{
    const val NOTE_ID_ARG = "noteId"
}

object NotesDestinations{
    const val NOTES_ROUTE = "$NOTES_SCREEN"
    const val ADD_EDIT_NOTES_ROUTE = "$ADD_EDIT_NOTES_SCREEN/{$NOTE_ID_ARG}"
}


class NotesNavigationActions(private val navController: NavController) {
    fun navigateToNotes(){
        navController.navigate(NOTES_ROUTE){
            launchSingleTop = true
        }
    }

    fun navigateToAddEditNote(noteId: Int){
        navController.navigate("$ADD_EDIT_NOTES_SCREEN/$noteId")

    }

}