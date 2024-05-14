package com.example.codaquest.domain.models

data class Filters(
    val searchText: String = "",
    val language: String = "",
    val length: String = "",
    val level: String = "Choose level",
    val levelExpanded: Boolean = false,
)
