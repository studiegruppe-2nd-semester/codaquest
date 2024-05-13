package com.example.codaquest.repositories

import android.content.ContentValues
import android.util.Log
import com.example.codaquest.models.Project
import com.example.codaquest.models.stringToLevelType
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class ProjectRepository {
    private val db = Firebase.firestore

    fun fetchProjects(
        uid: String,
        onSuccess: (MutableList<Project>) -> Unit,
    ) {
        db.collection("projects")
            .whereEqualTo("uid", uid)
            .get()
            .addOnSuccessListener { documents ->
                val projects: MutableList<Project> = documents.map { document ->
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
                }.toMutableList()

                onSuccess(projects)
            }
    }

    fun saveUserProject(
        project: Project,
        onSuccess: (Project) -> Unit,
    ) {
        db.collection("projects")
            .add(project)
            .addOnSuccessListener { documentReference ->
                Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: $documentReference")
                onSuccess(
                    project.copy(projectId = documentReference.id),
                )
            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding document", e)
            }
    }

    fun deleteSavedProject(
        projectId: String,
        onSuccess: () -> Unit,
    ) {
        db.collection("projects").document(projectId)
            .delete()
            .addOnSuccessListener {
                onSuccess()
            }
    }
}
