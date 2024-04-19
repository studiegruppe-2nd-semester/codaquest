package com.example.codaquest.ui.components.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.codaquest.ui.components.SharedViewModel

@Composable
fun LoginComposable (
    navController: NavController,
    sharedViewModel: SharedViewModel,
    loginViewModel: LoginViewModel
) {
    Column (modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(value = loginViewModel.email,
            onValueChange = {loginViewModel.updateEmail(it)},
            label = { Text("Email")})

        TextField(value = loginViewModel.password,
            onValueChange = {loginViewModel.updatePassword(it)},
            label = { Text("Password")})

        Button(onClick = {
            loginViewModel.login(
                navController = navController,
                sharedViewModel = sharedViewModel,
                loginViewModel = loginViewModel
            )
        }) {
        Text(text = "Log In")
        }

        Text(text = loginViewModel.error)
    }
}

