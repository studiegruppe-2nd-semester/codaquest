package com.example.codaquest.classes

data class User(
    val userUid: String,
    val username: String?,
    var onboardingData: OnboardingData? = null,
    var projects: MutableList<Project>? = null
)