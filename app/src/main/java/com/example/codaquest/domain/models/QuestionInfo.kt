package com.example.codaquest.domain.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class QuestionInfo(
    val question: String,
    val options: List<String>? = null,
    val type: QuestionTypes,
    var answer: MutableState<String> = mutableStateOf(""),
)
