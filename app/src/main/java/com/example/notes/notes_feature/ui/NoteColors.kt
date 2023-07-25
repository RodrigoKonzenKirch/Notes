package com.example.notes.notes_feature.ui

import androidx.core.graphics.toColorInt

sealed class NoteColors(
    val blue: Int = "#FF006AFF".toColorInt(),
    val yellow: Int = "#FF8C7200".toColorInt(),
    val green: Int = "#FF328800".toColorInt(),
    val red: Int = "#FFEB0A0F".toColorInt(),
    val purple: Int = "#FFC838A5".toColorInt(),
    val grey: Int = "#FF888888".toColorInt()
)
