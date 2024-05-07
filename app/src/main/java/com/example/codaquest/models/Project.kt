package com.example.codaquest.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Project(
    // USER ID
    var uid: String? = null,
    var projectId: String? = null,

    // Svar fra chat gpt titel
    var title: String? = null,

    // Indtastning
    var keywords: String? = null,
    var language: String? = null,
    val length: Int? = null,
    val level : LevelType? = LevelType.Beginner,
    // This should be lvl!!!!
// This should be lvl!!!!
    // This should be lvl!!!!
    // This should be lvl!!!!


    // Svar fra chat gpt beskrivelse
    val description: String? = null,

    // Svar fra chat gpt planen
    val steps: List<String>? = null,
)

