package com.example.codaquest.models

data class OnboardingData(

    val level: Level? = null,
    val languages: String? = null,
    val projectLength: Int? = null,
)

enum class Level {
    Beginner,
    Intermediate,
    Advanced,
}

// This is a function that makes it possible for us to make the
// code ".toEnum<Level>()" in UserRepository and Onboarding viewmodel.

// Define the enumValueOfOrNull function
inline fun <reified T : Enum<T>> enumValueOfOrNull(name: String): T? =
    enumValues<T>().find { it.name == name }

// Define the toEnum extension function
inline fun <reified T : Enum<T>> String?.toEnum(): T? =
    this?.let { enumValueOfOrNull<T>(it) }
