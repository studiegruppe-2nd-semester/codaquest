package com.example.codaquest.ui.components.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.codaquest.data.services.AccountService
import com.example.codaquest.domain.interfaces.ErrorOperations
import com.example.codaquest.domain.models.LoginInfo
import com.example.codaquest.domain.models.LoginState
import com.example.codaquest.domain.models.User
import com.example.codaquest.util.LoginSignupUtil
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

//        if (loginInfo.email.isEmpty() || loginInfo.password.isEmpty()) {
//            showError("Please enter email and password")
//            return
//        } else {
//            showError("")
//        }

        val validateLogin: ValidationResult = LoginSignupUtil.validateLogin(loginInfo)

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
        if (loginInfo.password == loginInfo.confirmPassword) {
            showError("")

            if (loginInfo.email.isNotEmpty() && loginInfo.username.isNotEmpty() && loginInfo.password.isNotEmpty()) {
                accountService.signUp(
                    loginInfo = loginInfo,
                    errorOperations = this,
                    onSuccess = { onSuccess(it) },
                )
            }
        } else {
            showError("Confirmed password does not match")
        }
    }

    // ----------------------------------------- ERROR
    override var error: String by mutableStateOf("")

    override fun showError(error: String) {
        this.error = error
    }
}
