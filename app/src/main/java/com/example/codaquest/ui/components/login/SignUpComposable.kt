package com.example.codaquest.ui.components.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SignUpComposable(loginViewModel: LoginViewModel) {
    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = loginViewModel.username,
            onValueChange = { loginViewModel.username },
            label = { Text("Username")})

        TextField(value = loginViewModel.password,
            onValueChange = { loginViewModel.password },
            label = { Text("Password")})

        TextField(value = loginViewModel.passwordConfirm,
            onValueChange = { loginViewModel.updatePasswordConfirm(it) },
            label = { Text("Confirm password")})

        Button(onClick = { /*TODO*/ }) {
            Text("Sign Up")
        }
    }
}