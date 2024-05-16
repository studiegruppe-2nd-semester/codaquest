package com.example.codaquest.ui.components.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.codaquest.data.services.AccountService
import com.example.codaquest.interfaces.ErrorOperations
import com.example.codaquest.domain.models.LoginInfo
import com.example.codaquest.domain.models.LoginState
import com.example.codaquest.domain.models.User
import com.example.codaquest.util.UserValidationUtil
import com.example.codaquest.util.ValidationResult

class LoginViewModel : ViewModel(), ErrorOperations {
    // ----------------------------------------- ACCOUNT SERVICE
    private val accountService = AccountService()

    // ----------------------------------------- LOGIN/SIGNUP STATE
    var state: LoginState by mutableStateOf(LoginState.Login)
        private set
    var stateButtonText by mutableStateOf("Create new account")
        private set
    fun updateState() {
        state = if (state == LoginState.Login) LoginState.Signup else LoginState.Login
        stateButtonText = if (stateButtonText == "Create new account") "Login" else "Create new account"
    }

    // ----------------------------------------- USERNAME
    var loginInfo: LoginInfo by mutableStateOf(LoginInfo())

    // ----------------------------------------- LOGIN
    fun login(
        onSuccess: (User) -> Unit,
    ) {
        showError("")

        val validateLogin: ValidationResult = UserValidationUtil.validateLogin(loginInfo)

        if (validateLogin is ValidationResult.Error) {
            showError(validateLogin.message)
        } else {
            accountService.login(
                loginInfo = loginInfo,
                errorOperations = this,
                onSuccess = { onSuccess(it) },
            )
        }
    }

    // ----------------------------------------- SIGN UP
    fun signUp(onSuccess: (User) -> Unit) {
        val validateSignUp: ValidationResult = UserValidationUtil.validateSignUp(loginInfo)

        if (validateSignUp is ValidationResult.Error) {
            showError(validateSignUp.message)
        } else {
            accountService.signUp(
                loginInfo = loginInfo,
                errorOperations = this,
                onSuccess = { onSuccess(it) },
            )
        }
    }

    // ----------------------------------------- ERROR
    override var error: String by mutableStateOf("")

    override fun showError(error: String) {
        this.error = error
    }
}
