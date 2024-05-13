package com.example.codaquest.domain.models

enum class LevelType {
    Beginner,
    Intermediate,
    Advanced,
}

// This function we use to  convert a string to enum class.
fun stringToLevelType(levelString: String): LevelType {
    return when (levelString) {
        "Beginner" -> LevelType.Beginner
        "Intermediate" -> LevelType.Intermediate
        "Advanced" -> LevelType.Advanced
        else -> LevelType.Beginner // Handle the default case appropriately
    }
}
