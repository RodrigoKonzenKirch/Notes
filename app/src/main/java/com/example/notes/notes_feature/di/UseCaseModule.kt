package com.example.notes.notes_feature.di

import com.example.notes.notes_feature.aplication.GetNoteUseCase
import com.example.notes.notes_feature.aplication.GetNoteUseCaseImpl
import com.example.notes.notes_feature.aplication.InsertNoteUseCase
import com.example.notes.notes_feature.aplication.InsertNoteUseCaseImpl
import com.example.notes.notes_feature.aplication.UpsertNoteUseCase
import com.example.notes.notes_feature.aplication.UpsertNoteUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Singleton
    @Binds
    abstract fun bindInsertNoteUseCase(insertNoteUseCaseImpl: InsertNoteUseCaseImpl): InsertNoteUseCase

    @Singleton
    @Binds
    abstract fun bindGetNoteUseCase(getNoteUseCaseImpl: GetNoteUseCaseImpl): GetNoteUseCase

    @Singleton
    @Binds
    abstract fun bindUpsertNoteUseCase(upsertNoteUseCaseImpl: UpsertNoteUseCaseImpl): UpsertNoteUseCase

}