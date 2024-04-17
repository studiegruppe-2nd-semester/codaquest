package com.example.codaquest.classes

data class Project(
    var title: String,
    val language: String,
    val length: Int?,
    val level: String,
    val description: String,
    val requirements: List<String>
)