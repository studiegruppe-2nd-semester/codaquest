package com.example.codaquest.classes

import kotlinx.serialization.Serializable

@Serializable
data class ApiRespond (
    val title: String,
    val description : String,
    val steps : List<String>,
    val level : Int,
    val language : String,
    val length : Int
)
