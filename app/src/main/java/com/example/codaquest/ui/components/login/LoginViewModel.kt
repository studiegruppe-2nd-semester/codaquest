package com.example.codaquest.ui.components.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.codaquest.classes.User
import com.example.codaquest.services.AccountService
import com.example.codaquest.ui.components.SharedViewModel

class LoginViewModel: ViewModel() {
    // ----------------------------------------- ACCOUNT SERVICE
    val accountService = AccountService()

    // ----------------------------------------- USERNAME
    var username: String by mutableStateOf("")
        private set
    fun updateUsername(newValue: String) {
        username = newValue
    }

    // ----------------------------------------- EMAIL
    var email: String by mutableStateOf("")
        private set
    fun updateEmail(newValue: String) {
        email = newValue
    }

    // ----------------------------------------- PASSWORD
    var password: String by mutableStateOf("")
        private set
    fun updatePassword(newValue: String) {
        password = newValue
    }

    // ----------------------------------------- PASSWORD CHECK
    var passwordCheck: String by mutableStateOf("")
        private set
    fun updatePasswordCheck(newValue: String) {
        passwordCheck = newValue
    }

    // ----------------------------------------- LOGIN
    fun login(navController: NavController, sharedViewModel: SharedViewModel) {
        if (email.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty()) {
            accountService.login(email, password, navController, sharedViewModel)
        }
    }

    // ----------------------------------------- SIGN UP
}