package com.example.codaquest.services

import androidx.navigation.NavController
import com.example.codaquest.classes.User
import com.example.codaquest.interfaces.ErrorOperations
import com.example.codaquest.interfaces.UserOperations
import com.example.codaquest.repositories.UserRepository
import com.example.codaquest.ui.components.SharedViewModel
import com.example.codaquest.ui.components.login.LoginViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

class AccountService {

    private val userRepository = UserRepository()

    fun signUp(
        email: String,
        username: String,
        password: String,
        errorOperations: ErrorOperations,
        onSuccess: (User) -> Unit
    ) {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val user = Firebase.auth.currentUser
                if (user != null) {
                    userRepository.addUserData(user.uid, username, onSuccess = { onSuccess(it) })
                }
            }
            .addOnFailureListener {
                errorOperations.showError("User already exists")
            }
    }

    fun login(
        email: String,
        password: String,
        errorOperations: ErrorOperations,
        onSuccess: (User) -> Unit
    ) {
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val user = Firebase.auth.currentUser
                if (user != null) {
                    userRepository.getUserData(user.uid, onSuccess = { onSuccess(it) })
                }
            }
            .addOnFailureListener {
                errorOperations.showError("Wrong username or password")
            }
    }

    fun getCurrentUser(): FirebaseUser? {
        return Firebase.auth.currentUser
    }

    fun logout(
        onSuccess: () -> Unit
    ) {
        Firebase.auth.signOut()
        onSuccess()
    }
}