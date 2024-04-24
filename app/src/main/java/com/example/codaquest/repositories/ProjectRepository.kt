package com.example.codaquest.repositories

import android.content.ContentValues
import android.util.Log
import androidx.navigation.NavController
import com.example.codaquest.classes.Project
import com.example.codaquest.classes.User
import com.example.codaquest.ui.components.SharedViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class ProjectRepository {
    private val db = Firebase.firestore

    fun getProjectData(
        projectID: String,
        navController: NavController?,
        sharedViewModel: SharedViewModel
    ) {
        db.collection("projects").document(projectID).get()
            .addOnSuccessListener { document ->

                val projectData = Project(
                    title = document.data?.get("title")?.toString(),
                    keywords = document.data?.get("keywords")?.toString(),
                    language = document.data?.get("language")?.toString(),
                    length = document.data?.get("length")?.toString()?.toInt(),
                    level = document.data?.get("level")?.toString(),
                    description = document.data?.get("description")?.toString(),
                    steps = when (val stepsData = document.data?.get("steps")) {
                        is List<*> -> stepsData.filterIsInstance<String>()
                        else -> emptyList()
                    }
                )
            }
    }
    fun getProjects(
        uid: String,
        sharedViewModel: SharedViewModel
    ) {
        db.collection("projects")
            .whereEqualTo("uid", uid)
            .get()
            .addOnSuccessListener { documents ->
                documents.forEach { document ->
                    val newProject = Project(
                        title = document.data["title"]?.toString(),
                        keywords = document.data["keywords"]?.toString(),
                        language = document.data["language"]?.toString(),
                        length = document.data["length"]?.toString()?.toInt(),
                        level = document.data["level"]?.toString(),
                        description = document.data["description"]?.toString(),
                        steps = when (val stepsData = document.data["steps"]) {
                            is List<*> -> stepsData.filterIsInstance<String>()
                            else -> emptyList()
                        }
                    )

                    val projects = sharedViewModel.user?.projects
                    projects?.add(newProject)

                    sharedViewModel.changeUser(
                        sharedViewModel.user?.copy(
                            projects = projects
                        )
                    )
                }
            }
    }

    fun addProjectData(
    projectID: String,
    title: String,
    keywords: String,
    language: String,
    length: Int,
    level: String,
    steps: List<String>,
    navController: NavController,
    sharedViewModel: SharedViewModel
    ) {
    val dataMap: Map<String, String> = mapOf(
        "title" to title
    )
    db.collection("projects").document().set(dataMap)
        .addOnSuccessListener {

            val projects = sharedViewModel.user?.projects
            projects?.add(
                Project(
                    title = title,
                    keywords = keywords,
                    language = language,
                    length = length,
                    level = level,
                    steps = steps
                )
            )

            sharedViewModel.changeUser(

                sharedViewModel.user?.copy(
                    projects = projects
                )
            )
            navController.navigate("home")
        }
        .addOnFailureListener { e ->
            Log.d("","")
        }
    }





    fun addProject(project: Project) {
        // Create a new user with a first and last name.
        // Here Firestore will create a DocumentId but we dont need to add it when creating an object

        // Add a new document with a generated ID
        db.collection("projects")
            .add(project)
            .addOnSuccessListener { documentReference ->
                Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference}")
            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding document", e)
            }
    }




}