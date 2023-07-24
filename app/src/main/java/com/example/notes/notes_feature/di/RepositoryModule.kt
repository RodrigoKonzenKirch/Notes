package com.example.notes.notes_feature.di

import com.example.notes.notes_feature.data.NotesRepositoryImpl
import com.example.notes.notes_feature.domain.NotesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideNotesRepository(repository: NotesRepositoryImpl): NotesRepository

}