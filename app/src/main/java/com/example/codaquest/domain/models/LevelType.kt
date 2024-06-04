package com.example.codaquest.domain.models
// Kasper
enum class LevelType {
    Beginner,
    Intermediate,
    Advanced,
}

fun stringToLevelType(levelString: String): LevelType {
    return when (levelString) {
        "Beginner" -> LevelType.Beginner
        "Intermediate" -> LevelType.Intermediate
        "Advanced" -> LevelType.Advanced
        else -> LevelType.Beginner
    }
}
