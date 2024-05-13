package com.example.codaquest.util

import com.example.codaquest.domain.models.LoginInfo

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

    fun emailValidation(email: String): Boolean {
//        if (email.isEmpty()) {
//            return false
//        }
//
//        if (email.length < 8) { // minimum: aa@aa.aa
//            return false
//        }
//
//        if (!email.contains("@")) {
//            return false
//        }
//
//        if (!email.contains(".")) {
//            return false
//        }
//
//        return true

        // ANOTHER WAY
        val emailRegex = "^A-Za-z*@[A-Za-z0-9-]+\\.[A-Za-z]{2,}$"
        return email.matches(emailRegex.toRegex())
    }

    fun passwordValidation(password: String): Boolean {
        if (password.isEmpty()) {
            return false
        }

        if (password.length < 6) {
            return false
        }

        if (password.count { it.isDigit() } < 2) {
            return false
        }

        if (password.count { it.isUpperCase() } < 1) {
            return false
        }

        if (password.count { it.isLowerCase() } < 1) {
            return false
        }

        return true
    }
}
