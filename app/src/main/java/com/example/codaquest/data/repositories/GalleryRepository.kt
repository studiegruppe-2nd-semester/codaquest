package com.example.codaquest.data.repositories

import android.content.ContentValues
import android.util.Log
import com.example.codaquest.domain.models.Project
import com.example.codaquest.domain.models.stringToLevelType
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class GalleryRepository {
    private val db = Firebase.firestore

    fun fetchGalleryProjects(
        onSuccess: (List<Project>) -> Unit,
    ) {
        db.collection("gallery")
            .get()
            .addOnSuccessListener { documents ->
                val projects: List<Project> = documents.map { document ->
                    Project(
                        projectId = document.id,
                        uid = document.data["uid"]?.toString(),
                        title = document.data["title"]?.toString(),
                        language = document.data["language"]?.toString(),
                        length = document.data["length"]?.toString()?.toInt(),
                        level = document.data["level"]?.toString()?.let { stringToLevelType(it) },
                        description = document.data["description"]?.toString(),
                        steps = when (val stepsData = document.data["steps"]) {
                            is List<*> -> stepsData.filterIsInstance<String>()
                            else -> emptyList()
                        },
                    )
                }

                onSuccess(projects)
            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error fetching gallery projects", e)
            }
    }
}
