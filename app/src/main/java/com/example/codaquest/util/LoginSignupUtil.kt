package com.example.codaquest.util

import com.example.codaquest.models.LoginInfo

object LoginSignupUtil {

    /*
    Login is not valid if...
    ...the username is empty
    ...the password is empty
     */
    fun validateLogin(
        loginInfo: LoginInfo,
    ): ValidationResult {
        if (loginInfo.email.isEmpty()) {
            return ValidationResult.Error("Email cannot be empty")
        }

        if (loginInfo.password.isEmpty()) {
            return ValidationResult.Error("Password cannot be empty")
        }

        return ValidationResult.Success
    }
}
