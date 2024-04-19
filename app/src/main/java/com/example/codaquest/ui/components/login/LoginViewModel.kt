package com.example.codaquest.ui.components.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.codaquest.classes.LoginState
import com.example.codaquest.services.AccountService
import com.example.codaquest.ui.components.SharedViewModel

class LoginViewModel: ViewModel() {
    // ----------------------------------------- ACCOUNT SERVICE
    private val accountService = AccountService()

    // ----------------------------------------- LOGIN/SIGNUP STATE
    var state: LoginState by mutableStateOf(LoginState.login)
    var stateButtonText by mutableStateOf("Create new account")
        private set
    fun updateState() {
        state = if (state == LoginState.login) LoginState.signup else LoginState.login
        stateButtonText = if (stateButtonText == "Create new account") "Login" else "Create new account"
    }


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
    var passwordConfirm: String by mutableStateOf("")
        private set
    fun updatePasswordConfirm(newValue: String) {
        passwordConfirm = newValue
    }

    // ----------------------------------------- LOGIN
    fun login(navController: NavController, sharedViewModel: SharedViewModel, loginViewModel: LoginViewModel) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            accountService.login(
                email = email,
                password = password,
                navController = navController,
                sharedViewModel = sharedViewModel,
                loginViewModel = loginViewModel
            )
        }
    }

    // ----------------------------------------- SIGN UP

    // ----------------------------------------- ERROR
    var error: String by mutableStateOf("")

}