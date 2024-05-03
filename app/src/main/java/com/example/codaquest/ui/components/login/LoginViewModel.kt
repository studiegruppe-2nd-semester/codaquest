package com.example.codaquest.ui.components.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.codaquest.Models.LoginState
import com.example.codaquest.Models.User
import com.example.codaquest.interfaces.ErrorOperations
import com.example.codaquest.services.AccountService

class LoginViewModel: ViewModel(), ErrorOperations {
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

    // ----------------------------------------- EMAIL
    var email: String by mutableStateOf("")

    // ----------------------------------------- PASSWORD
    var password: String by mutableStateOf("")

    // ----------------------------------------- PASSWORD CONFIRM
    var passwordConfirm: String by mutableStateOf("")

    // ----------------------------------------- LOGIN
    fun login(
        errorOperations: ErrorOperations,
        onSuccess: (User) -> Unit
    ) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            accountService.login(
                email = email,
                password = password,
                errorOperations = errorOperations,
                onSuccess = { onSuccess(it) }
            )
        }
    }

    // ----------------------------------------- SIGN UP
    fun signUp(onSuccess: (User) -> Unit) {
        if (password == passwordConfirm) {
            error = ""

            if (email.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty()) {
                accountService.signUp(
                    email = email,
                    username = username,
                    password = password,
                    this,
                    onSuccess = { onSuccess(it) }
                )
            }
        }
        else {
            error = "Confirmed password does not match"
        }
    }

    // ----------------------------------------- ERROR
    var error: String by mutableStateOf("")
        private set
    override fun showError(error: String) {
        this.error = error
    }

}