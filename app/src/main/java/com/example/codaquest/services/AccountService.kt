package com.example.codaquest.services

import androidx.navigation.NavController
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
        navController: NavController,
        sharedViewModel: SharedViewModel,
        loginViewModel: LoginViewModel
    ) {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                loginViewModel.error = ""

                val user = Firebase.auth.currentUser
                if (user != null) {
                    userRepository.addUserData(user.uid, username, navController, sharedViewModel)
                }
            }
            .addOnFailureListener {
                loginViewModel.error = "User already exists"
            }
    }

    fun login(
        email: String,
        password: String,
        navController: NavController,
        sharedViewModel: SharedViewModel,
        loginViewModel: LoginViewModel
    ) {
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                loginViewModel.error = ""

                val user = Firebase.auth.currentUser
                if (user != null) {
                    userRepository.getUserData(user.uid, navController, sharedViewModel)
                }
            }
            .addOnFailureListener {
                loginViewModel.error = "Wrong username or password"
            }
    }

    fun getCurrentUser(): FirebaseUser? {
        return Firebase.auth.currentUser
    }

    fun logout(navController: NavController) {
        Firebase.auth.signOut()
        navController.navigate("home")
    }
}