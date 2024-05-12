package com.example.codaquest

import com.example.codaquest.models.LoginInfo
import com.example.codaquest.util.LoginSignupUtil
import com.example.codaquest.util.ValidationResult
import org.junit.Assert.assertTrue
import org.junit.Test

class LoginSignupUtilTest {
    @Test
    fun `empty username returns false`() {
        val result = LoginSignupUtil.validateLogin(
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
    fun `empty password returns false`() {
        val result = LoginSignupUtil.validateLogin(
            LoginInfo(
                email = "username",
                password = "",
            ),
        )
        assertTrue(result is ValidationResult.Error)
    }
}
