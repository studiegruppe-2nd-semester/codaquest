package com.example.codaquest.data.repositories

import android.content.ContentValues
import android.util.Log
import com.example.codaquest.domain.models.Project
import com.example.codaquest.domain.models.stringToLevelType
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
// Kasper
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
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error fetching saved user projects", e)
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
                Log.w(ContentValues.TAG, "Error in adding project to user", e)
            }
    }

    // Ane
    fun deleteSavedProject(
        projectId: String,
        onSuccess: () -> Unit,
    ) {
        db.collection("projects").document(projectId)
            .delete()
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error deleting project from user", e)
            }
    }

    // Nathasja
    fun deleteAllUserProjects(
        uid: String,
        onSuccess: () -> Unit,
    ) {
        val batch = db.batch()

        db.collection("projects")
            .whereEqualTo("uid", uid)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val querySnapshot = task.result
                    if (querySnapshot != null) {
                        querySnapshot.forEach { document ->
                            batch.delete(document.reference)
                        }

                        batch.commit()
                            .addOnSuccessListener {
                                onSuccess()
                                Log.d("DELETE", "Projects successfully deleted!")
                            }
                            .addOnFailureListener { e ->
                                Log.w("DELETE", "Error deleting projects", e)
                            }
                    } else {
                        Log.w("DELETE", "Query snapshot is null")
                    }
                } else {
                    val exception = task.exception ?: Exception("Unknown error occurred during Firestore query")
                    Log.w("DELETE", "Error querying projects", exception)
                }
            }
    }
}
