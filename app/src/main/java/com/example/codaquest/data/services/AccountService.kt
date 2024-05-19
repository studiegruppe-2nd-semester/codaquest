package com.example.codaquest.data.services

import android.util.Log
import com.example.codaquest.data.repositories.UserRepository
import com.example.codaquest.domain.interfaces.ErrorOperations
import com.example.codaquest.domain.models.LoginInfo
import com.example.codaquest.domain.models.User
import com.google.firebase.Firebase
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import java.lang.Exception

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
                    userRepository.addUserData(user.uid, loginInfo.username, loginInfo.email, onSuccess = { onSuccess(it) })
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

    // Nathasja
    fun updatePassword(
        loginInfo: LoginInfo,
        newPassword: String,
        onResult: (Boolean, String) -> Unit,
    ) {
        val user = Firebase.auth.currentUser

        val credential = EmailAuthProvider
            .getCredential(loginInfo.email, loginInfo.password)

        try {
            user?.reauthenticate(credential)
        } catch (e: Exception) {
            println("Error: $e")
            return
        }

        if (user != null) {
            user.updatePassword(newPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("AccountService", "User password updated successfully.")
                        onResult(true, "Password updated successfully.")
                    } else {
                        Log.e("AccountService", "Error updating password", task.exception)
                        onResult(false, task.exception?.message ?: "Failed to update password.")
                    }
                }
        } else {
            Log.e("AccountService", "No authenticated user.")
            onResult(false, "No authenticated user.")
        }
    }
}
