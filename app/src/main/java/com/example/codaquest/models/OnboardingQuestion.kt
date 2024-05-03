package com.example.codaquest.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class OnboardingQuestion(
    val question: String,
    val type: Enum<OnboardingQuestionTypes>,
    val options: List<String>? = null,
    var answer: MutableState<String> = mutableStateOf(""),
)
