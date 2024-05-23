package com.example.codaquest.data.repositories

import android.content.ContentValues
import android.util.Log
import com.example.codaquest.domain.models.OnboardingData
import com.example.codaquest.domain.models.User
import com.example.codaquest.domain.models.stringToLevelType
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class UserRepository {
    private val db = Firebase.firestore
    private val projectRepository = ProjectRepository()

    fun getKey(
        onSuccess: (String?) -> Unit,
    ) {
        db.collection("api").document("information").get()
            .addOnSuccessListener { document ->
                document.data?.get("key")?.toString()?.let { onSuccess(it) }
            }
            .addOnFailureListener { e ->
                println("Error fetching key: $e")
            }
    }

    fun getUserData(
        userUid: String,
        onSuccess: (User) -> Unit,
    ) {
        db.collection("users").document(userUid).get()
            .addOnSuccessListener { document ->
                val onboardingData = OnboardingData(
                    level = document.data?.get("level")?.toString()?.let { stringToLevelType(it) },
                    languages = document.data?.get("languages")?.toString(),
                    projectLength = document.data?.get("project-length")?.toString()?.toInt(),
                )

                projectRepository.fetchProjects(userUid) { projects ->
                    onSuccess(
                        User(
                            userUid = document.id,
                            username = document.data?.get("username")?.toString(),
                            email = document.data?.get("email")?.toString(),
                            onboardingData = onboardingData,
                            projects = projects,
                        ),

                    )
                }

                Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting user data: ", exception)
            }
    }

    fun addUserData(
        uid: String,
        username: String,
        email: String,
        onSuccess: (User) -> Unit,
    ) {
        val dataMap: Map<String, String> = mapOf(
            "username" to username,
            "email" to email,
        )
        db.collection("users").document(uid).set(dataMap)
            .addOnSuccessListener {
                onSuccess(
                    User(
                        userUid = uid,
                        username = username,
                        email = email,
                    ),
                )
            }
            .addOnFailureListener { e ->
                Log.d("addData", "Error adding user data: $e")
            }
    }

    fun addOnboardingDataToUserData(
        onboardingData: OnboardingData,
        user: User,
        onSuccess: (User) -> Unit,
    ) {
        // https://stackoverflow.com/questions/56608046/update-a-document-in-firestore

        db.collection("users").document(user.userUid).update(
            "level",
            onboardingData.level,
            "languages",
            onboardingData.languages,
            "project-length",
            onboardingData.projectLength,
        )
            .addOnSuccessListener {
                Log.d("update", "Update success")

                onSuccess(
                    user.copy(
                        onboardingData = OnboardingData(
                            level = onboardingData.level,
                            languages = onboardingData.languages,
                            projectLength = onboardingData.projectLength,
                        ),
                    ),
                )
            }
            .addOnFailureListener { e ->
                Log.d("update", "Error updating user's onboarding data: $e")
            }
    }

    fun deleteUserData(
        uid: String,
        onSuccess: () -> Unit,
    ) {
        db.collection("users").document(uid)
            .delete()
            .addOnSuccessListener {
                projectRepository.deleteAllUserProjects(
                    uid,
                    onSuccess = onSuccess,
                )
                Log.d("DELETE", "DocumentSnapshot successfully deleted!")
            }
            .addOnFailureListener { e ->
                Log.w("DELETE", "Error deleting document", e)
            }
    }
}
