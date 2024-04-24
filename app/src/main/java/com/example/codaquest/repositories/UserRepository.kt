package com.example.codaquest.repositories

import android.content.ContentValues
import android.util.Log
import androidx.navigation.NavController
import com.example.codaquest.classes.OnboardingData
import com.example.codaquest.classes.User
import com.example.codaquest.ui.components.SharedViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class UserRepository {
    private val db = Firebase.firestore

    fun getKey(
        sharedViewModel: SharedViewModel
    ) {
        db.collection("api").document("information").get()
            .addOnSuccessListener { document ->
                document.data?.get("key")?.toString()?.let { sharedViewModel.updateKey(it) }
            }
    }

    fun getUserData(
        userUid: String,
        navController: NavController?,
        sharedViewModel: SharedViewModel
    ) {
        db.collection("users").document(userUid).get()
            .addOnSuccessListener { document ->

                val onboardingData = OnboardingData(
                    level = document.data?.get("level")?.toString(),
                    languages = document.data?.get("languages")?.toString(),
                    projectLength = document.data?.get("project-length")?.toString()?.toInt()
                )

                sharedViewModel.changeUser(User(
                    userUid = document.id,
                    username = document.data?.get("username")?.toString(),
                    onboardingData = onboardingData
                ))

                navController?.navigate("profile")

              Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
                navController?.navigate("profile")
            }
    }

    fun addUserData(
        uid: String,
        username: String,
        navController: NavController,
        sharedViewModel: SharedViewModel
    ) {
        val dataMap: Map<String, String> = mapOf(
            "username" to username
        )
        db.collection("users").document(uid).set(dataMap)
            .addOnSuccessListener {
                sharedViewModel.changeUser(
                    User(
                    userUid = uid,
                    username = username
                )
                )
                navController.navigate("onboarding")
            }
            .addOnFailureListener { e ->
                Log.d("addData", "addData failure: $e")
            }


    }

    fun updateUserData (
        onboardingData: OnboardingData,
        navController: NavController,
        sharedViewModel: SharedViewModel
        ) {
        // https://stackoverflow.com/questions/56608046/update-a-document-in-firestore
        sharedViewModel.user?.let {
            db.collection("users").document(it.userUid).update(
                "level", onboardingData.level,
                "languages", onboardingData.languages,
                "project-length", onboardingData.projectLength
            )
                .addOnSuccessListener {
                    Log.d("update", "Update success")
                    val user = sharedViewModel.user
                    if (user != null) {
                        sharedViewModel.changeUser(User(
                            userUid = user.userUid,
                            username = user.username,
                            onboardingData = OnboardingData(
                                level = onboardingData.level,
                                languages = onboardingData.languages,
                                projectLength = onboardingData.projectLength
                            )
                        ))
                        navController.navigate("profile")
                    }
                }
                .addOnFailureListener { e ->
                    Log.d("update", "update failure: $e")
                }
        }
    }

//    fun addNote(note: Note) {
//        // Create a new user with a first and last name.
//        // Here Firestore will create a DocumentId but we dont need to add it when creating an object
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