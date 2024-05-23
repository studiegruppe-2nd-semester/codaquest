package com.example.codaquest.data.services

import android.util.Log
import com.example.codaquest.data.repositories.UserRepository
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
        onError: (String) -> Unit,
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
                onError("User already exists")
            }
    }

    fun login(
        loginInfo: LoginInfo,
        onError: (String) -> Unit,
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
                onError("User not found")
            }
    }

    fun getCurrentUser(): FirebaseUser? {
        return Firebase.auth.currentUser
    }

    fun logout(
        onSuccess: () -> Unit,
    ) {
        Firebase.auth.signOut()
        println("logout successful")
        onSuccess()
    }

    private fun reAuthenticate(
        user: FirebaseUser,
        email: String,
        password: String,
    ): Boolean {
        println("Trying to re-authenticate...")
        println(email)
        println(password)

        val credential = EmailAuthProvider
            .getCredential(email, password)

        try {
            user.reauthenticate(credential)
            return true
        } catch (e: Exception) {
            println("Error: $e")
            return false
        }
    }

    // Nathasja
    suspend fun updatePassword(
        loginInfo: LoginInfo,
        newPassword: String,
        onResult: (Boolean, String) -> Unit,
    ) {
        val user = Firebase.auth.currentUser!!

        val reAuthenticate = reAuthenticate(user, loginInfo.email, loginInfo.password)

        if (reAuthenticate) {
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

    fun deleteAccount(
        loginInfo: LoginInfo,
        onCompleted: () -> Unit,
        onError: (String) -> Unit,
    ) {
        val user = Firebase.auth.currentUser!!
        val uid = user.uid

        val reAuthenticate = reAuthenticate(user, loginInfo.email, loginInfo.password)

        if (reAuthenticate) {
            user.delete()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("Delete Account", "Account Deleted")
                        userRepository.deleteUserData(
                            uid,
                            onSuccess = {
                                logout(onSuccess = { onCompleted() })
                            },
                        )
                    } else {
                        Log.e("Delete Account", "Account Deletion Failed: ${task.exception}")
                        onError("Could not delete account")
                    }
                }
        } else {
            Log.e("AccountService", "No authenticated user.")
            onError("Could not re-authenticate")
        }
    }
}
