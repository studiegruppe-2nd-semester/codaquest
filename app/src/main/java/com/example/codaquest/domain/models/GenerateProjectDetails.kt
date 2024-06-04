package com.example.codaquest.domain.models

// Ane
data class GenerateProjectDetails(
    var keywords: String = "",
    var language: String = "",
    val length: Int = 0,
    val level: LevelType = LevelType.Beginner,
)
