package com.example.codaquest.domain.models

data class Filters(
    val searchText: String? = "",
    val language: String? = "",
    val languageExpanded: Boolean = false,
    val length: Int = 1,
    val level: LevelType = LevelType.Beginner,
    val levelExpanded: Boolean = false,
)
