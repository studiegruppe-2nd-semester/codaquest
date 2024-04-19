package com.example.codaquest.services

import androidx.navigation.NavHostController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

class AccountService {
    fun authenticate(
        email: String,
        password: String,
        navController: NavHostController,
//        viewModel: LoginViewModel
    ) {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
//                viewModel.updateUI(Firebase.auth.currentUser)
//                viewModel.error = ""
//                navController.navigate("success")
            }
            .addOnFailureListener {
//                viewModel.error = "User already exists"
            }
    }

    fun login(
        email: String,
        password: String,
        navController: NavHostController,
//        viewModel: LoginViewModel
    ) {
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
//                viewModel.updateUI(Firebase.auth.currentUser)
//                viewModel.error = ""
//                navController.navigate("success")
            }
            .addOnFailureListener {
//                viewModel.error = "Wrong username or password"
            }
    }

    fun getCurrentUser(): FirebaseUser? {
        return Firebase.auth.currentUser
    }
}