package com.example.codaquest.classes

data class Project(
    //Indtastning
    var keywords: String,

    //Svar fra chat gpt
    var title: String,

    //Indtastning
    val language: String,
    val hours: Int?,
    val level: String,

    // Svar fra chat gpt (planen)
    val description: String,

    // Den her ved jeg ikke hvad er...
    val requirements: List<String>
)
