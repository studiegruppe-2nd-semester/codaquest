package com.example.codaquest.domain.models

data class User(
    val userUid: String,
    val username: String?,
    val email: String?,
    var onboardingData: OnboardingData? = null,
    var projects: MutableList<Project>? = null,
)
