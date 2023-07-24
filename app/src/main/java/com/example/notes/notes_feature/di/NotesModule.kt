package com.example.notes.notes_feature.di

import android.content.Context
import androidx.room.Room
import com.example.notes.notes_feature.data.NotesDao
import com.example.notes.notes_feature.data.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotesModule {

    @Provides
    @Singleton
    fun providesNotesDatabase(
        @ApplicationContext appContext: Context,
    ): NotesDatabase {
        return Room.databaseBuilder(
            appContext,
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