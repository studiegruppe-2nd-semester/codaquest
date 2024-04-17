package com.example.codaquest.repositories

import android.content.ContentValues
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class UserRepository {
    private val db = Firebase.firestore

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