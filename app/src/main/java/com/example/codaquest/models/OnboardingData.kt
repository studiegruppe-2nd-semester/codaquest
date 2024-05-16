package com.example.codaquest.models

data class OnboardingData(

    val level: LevelType?,
    val languages: String? = null,
    val projectLength: Int? = null,
)

// This is a function that makes it possible for us to make the
// code ".toEnum<Level>()" in UserRepository and Onboarding viewmodel.
