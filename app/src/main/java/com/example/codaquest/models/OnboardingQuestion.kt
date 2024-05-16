package com.example.codaquest.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class OnboardingQuestion(
    val question: String,
    val options: List<String>? = null,
    val type: OnboardingQuestionTypes,
    var answer: MutableState<String> = mutableStateOf(""),
)
