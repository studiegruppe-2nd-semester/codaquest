package com.example.codaquest.services

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.codaquest.repositories.UserRepository
import com.example.codaquest.ui.components.SharedViewModel
import com.example.codaquest.ui.components.login.LoginViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

class AccountService {

    private val userRepository = UserRepository()

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
        navController: NavController,
        sharedViewModel: SharedViewModel
    ) {
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val user = Firebase.auth.currentUser
                if (user != null) {
                    userRepository.getUserData(user.uid, sharedViewModel)
                }
//                viewModel.error = ""
                navController.navigate("profile")
            }
            .addOnFailureListener {
//                viewModel.error = "Wrong username or password"
            }
    }

    fun getCurrentUser(): FirebaseUser? {
        return Firebase.auth.currentUser
    }
}