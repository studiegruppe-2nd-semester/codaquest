package com.example.codaquest.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Project(
    var uid: String? = null,
    var projectId: String? = null,

    var title: String? = null,

    var language: String? = null,
    val length: Int? = null,
    val level: LevelType? = LevelType.Beginner,

    val description: String? = null,

    val steps: List<String>? = null,
)
