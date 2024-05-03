package com.example.codaquest.repositories

import android.content.ContentValues
import android.util.Log
import com.example.codaquest.Models.Level
import com.example.codaquest.Models.OnboardingData
import com.example.codaquest.Models.User
import com.example.codaquest.Models.toEnum
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class UserRepository {
    private val db = Firebase.firestore
    private val projectRepository = ProjectRepository()

    fun getKey(
        onSuccess: (String?) -> Unit
    ) {
        db.collection("api").document("information").get()
            .addOnSuccessListener { document ->
                document.data?.get("key")?.toString()?.let { onSuccess(it) }
            }
            .addOnFailureListener { e ->
                println("Error getting documents: $e")
            }
    }

    fun getUserData(
        userUid: String,
        onSuccess: (User) -> Unit
    ) {
        db.collection("users").document(userUid).get()
            .addOnSuccessListener { document ->
                val onboardingData = OnboardingData(
                    level = document.data?.get("level")?.toString()?.toEnum<Level>(),
                    languages = document.data?.get("languages")?.toString(),
                    projectLength = document.data?.get("project-length")?.toString()?.toInt()
                )

                val user = User(
                    userUid = document.id,
                    username = document.data?.get("username")?.toString(),
                    onboardingData = onboardingData
                )

                projectRepository.getProjects(userUid, onSuccess = { onSuccess(user.copy(projects = it)) })

              Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
            }
    }

    fun addUserData(
        uid: String,
        username: String,
        onSuccess: (User) -> Unit
    ) {
        val dataMap: Map<String, String> = mapOf(
            "username" to username
        )
        db.collection("users").document(uid).set(dataMap)
            .addOnSuccessListener {
                onSuccess(User(
                    userUid = uid,
                    username = username
                ))
            }
            .addOnFailureListener { e ->
                Log.d("addData", "addData failure: $e")
            }


    }

    fun addOnboardingDataToUserData (
        onboardingData: OnboardingData,
        user: User,
        onSuccess: (User) -> Unit
        ) {
        // https://stackoverflow.com/questions/56608046/update-a-document-in-firestore

        db.collection("users").document(user.userUid).update(
            "level", onboardingData.level,
            "languages", onboardingData.languages,
            "project-length", onboardingData.projectLength
        )
            .addOnSuccessListener {
                Log.d("update", "Update success")

                onSuccess(user.copy(
                    onboardingData = OnboardingData(
                        level = onboardingData.level,
                        languages = onboardingData.languages,
                        projectLength = onboardingData.projectLength
                    )
                ))
            }
            .addOnFailureListener { e ->
                Log.d("update", "update failure: $e")
            }

    }

//    fun addNote(note: Note) {
//        // Create a new user with a first and last name.
//        // Here Firestore will create a DocumentId but we don't need to add it when creating an object
//
//        // Add a new document with a generated ID
//        db.collection("notes")
//            .add(note)
//            .addOnSuccessListener { documentReference ->
//                Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference}")
//            }
//            .addOnFailureListener { e ->
//                Log.w(ContentValues.TAG, "Error adding document", e)
//            }
//    }
//
//    suspend fun getCollection(): MutableList<Note> =
//        db.collection("notes")
//            .get()
//            .await()
//            .toObjects(Note::class.java)
//
//
//    fun getItemsFromCollection(equalToField: String, equalToValue: Any) {
//        db.collection("notes")
//            .whereEqualTo(equalToField,equalToValue)
//            .get()
//            .addOnSuccessListener { documents ->
//                for (document in documents) {
//                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
//            }
//    }
//
//    fun deleteItem(itemId: String) {
//        db.collection("notes").document(itemId)
//            .delete()
//            .addOnSuccessListener {
//                Log.d(ContentValues.TAG, "DocumentSnapshot successfully deleted!")
//            }
//            .addOnFailureListener { e ->
//                Log.w(ContentValues.TAG, "Error deleting document", e)
//            }
//    }
//
//    fun updateItem(itemId: String, item: Any) {
//        // https://stackoverflow.com/questions/56608046/update-a-document-in-firestore
//        db.collection("users").document(itemId).update(
//            "firstname", "John",
//            "lastname", "Smith",
//            "age", 25)
//            .addOnSuccessListener {
//
//            }
//            .addOnFailureListener {
//            }
//
////        db.collection("users").document(itemId).set(item)
//    }
}