package com.example.codaquest.classes

data class Project(
    // USER ID
    var uid: String? = null,
    var projectId: String? = null,

    // Svar fra chat gpt titel
    var title: String? = null,

    //Indtastning
    var keywords: String? = null,
    val language: String? = null,
    val length: Int? = null,
    val level: String? = null,

    // Svar fra chat gpt beskrivelse
    val description: String? = null,

    // Svar fra chat gpt planen
    val steps: List<String>? = null,
)
