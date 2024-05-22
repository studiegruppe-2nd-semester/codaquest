package com.example.codaquest

import com.example.codaquest.domain.models.LoginInfo
import com.example.codaquest.util.UserValidationUtil
import com.example.codaquest.util.ValidationResult
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class UserValidationUtilTest {
    // ------------------------------------- EMAIL
    @Test
    fun `empty username returns error`() {
        val result = UserValidationUtil.validateLogin(
            LoginInfo(
                email = "",
                password = "password",
            ),
        )
        assertTrue(result is ValidationResult.Error)

        // If you want to check the error message, you can do:
//        val errorMessage = (result as ValidationResult.Error).message
//        assertEquals("Email cannot be empty", errorMessage)
    }

    @Test
    fun `wrong email format returns error`() {
        val result = UserValidationUtil.validateLogin(
            LoginInfo(
                email = "@email.dk",
                password = "password",
            ),
        )
//        assertTrue(result is ValidationResult.Error)
        assertEquals("Wrong email format", (result as ValidationResult.Error).message)
    }

    @Test
    fun `wrong email format returns error 2`() {
        val result = UserValidationUtil.validateLogin(
            LoginInfo(
                email = "hi@.dk",
                password = "password",
            ),
        )
        assertEquals("Wrong email format", (result as ValidationResult.Error).message)
    }

    @Test
    fun `wrong email format returns error 3`() {
        val result = UserValidationUtil.validateLogin(
            LoginInfo(
                email = "hi@email",
                password = "password",
            ),
        )
//        assertTrue(result is ValidationResult.Error)
        assertEquals("Wrong email format", (result as ValidationResult.Error).message)
    }

    @Test
    fun `wrong email format returns error 4`() {
        val result = UserValidationUtil.validateLogin(
            LoginInfo(
                email = "hi@email.2com",
                password = "password",
            ),
        )
//        assertTrue(result is ValidationResult.Error)
        assertEquals("Wrong email format", (result as ValidationResult.Error).message)
    }

    @Test
    fun `valid email format returns success`() {
        val result = UserValidationUtil.validateLogin(
            LoginInfo(
                email = "email@email.com",
                password = "password",
            ),
        )
        assertTrue(result is ValidationResult.Success)
    }

    // ------------------------------------- PASSWORD
    @Test
    fun `empty password returns error`() {
        val result = UserValidationUtil.validateLogin(
            LoginInfo(
                email = "email@email.com",
                password = "",
            ),
        )
        assertTrue(result is ValidationResult.Error)
    }

    @Test
    fun `too short password returns error`() {
        val result = UserValidationUtil.validateLogin(
            LoginInfo(
                email = "email@email.com",
                password = "pass1",
            ),
        )
        assertTrue(result is ValidationResult.Error)
    }

    @Test
    fun `valid email and password returns success`() {
        val result = UserValidationUtil.validateLogin(
            LoginInfo(
                email = "email@email.com",
                password = "password",
            ),
        )
        assertTrue(result is ValidationResult.Success)
    }
}
