package com.example.notes.notes_feature.di

import android.app.Application
import androidx.room.Room
import com.example.notes.notes_feature.data.NotesDao
import com.example.notes.notes_feature.data.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotesModule {

    @Provides
    @Singleton
    fun providesNotesDatabase(
        application: Application,
    ): NotesDatabase {
        return Room.databaseBuilder(
            application,
            NotesDatabase::class.java,
            "notes_database"
        ).build()
    }

    @Provides
    @Singleton
    fun providesNotesDao(notesDatabase: NotesDatabase): NotesDao {
        return notesDatabase.notesDao()
    }


}