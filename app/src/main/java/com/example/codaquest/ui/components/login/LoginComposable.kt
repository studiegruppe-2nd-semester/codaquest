package com.example.codaquest.ui.components.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.codaquest.Greeting
import com.example.codaquest.ui.theme.CodaQuestTheme

@Composable
fun LoginComposable (
    navController: NavController,
    loginViewModel: LoginViewModel
) {
    Column (modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(value = loginViewModel.username,
            onValueChange = {loginViewModel.updateUsername(it)},
            label = { Text("Username")})

        TextField(value = loginViewModel.password,
            onValueChange = {loginViewModel.updatePassword(it)},
            label = { Text("Password")})

       Button(onClick = {}) {
           Text(text = "Log In")
       }
    }
}

