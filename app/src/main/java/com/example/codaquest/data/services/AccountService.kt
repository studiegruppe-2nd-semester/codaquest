package com.example.codaquest.data.services

import com.example.codaquest.data.repositories.UserRepository
import com.example.codaquest.domain.interfaces.ErrorOperations
import com.example.codaquest.domain.models.LoginInfo
import com.example.codaquest.domain.models.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

class AccountService {
    private val userRepository = UserRepository()

    fun signUp(
        loginInfo: LoginInfo,
        errorOperations: ErrorOperations,
        onSuccess: (User) -> Unit,
    ) {
        Firebase.auth.createUserWithEmailAndPassword(loginInfo.email, loginInfo.password)
            .addOnSuccessListener {
                val user = Firebase.auth.currentUser
                if (user != null) {
                    userRepository.addUserData(user.uid, loginInfo.username, onSuccess = { onSuccess(it) })
                }
            }
            .addOnFailureListener {
                errorOperations.showError("User already exists")
            }
    }

    fun login(
        loginInfo: LoginInfo,
        errorOperations: ErrorOperations,
        onSuccess: (User) -> Unit,
    ) {
        Firebase.auth.signInWithEmailAndPassword(loginInfo.email, loginInfo.password)
            .addOnSuccessListener {
                val user = Firebase.auth.currentUser
                if (user != null) {
                    userRepository.getUserData(
                        user.uid,
                        onSuccess = { newUser ->
                            onSuccess(newUser)
                        },
                    )
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
        onSuccess: () -> Unit,
    ) {
        Firebase.auth.signOut()
        onSuccess()
    }
}
