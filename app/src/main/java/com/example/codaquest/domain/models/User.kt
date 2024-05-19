package com.example.codaquest.domain.models

data class User(
    val userUid: String,
    val username: String?,
    val email: String? = null,
    var onboardingData: OnboardingData? = null,
    var projects: MutableList<Project>? = null,
)
