package com.example.codaquest.domain.models

import com.google.firebase.firestore.DocumentId
import kotlinx.serialization.Serializable

@Serializable
data class Project(
    var uid: String? = null,
    @DocumentId var projectId: String? = null,

    var title: String? = null,

    var language: String? = null,
    val length: Int? = null,
    val level: LevelType? = null,

    val description: String? = null,

    val steps: List<String>? = null,
)
