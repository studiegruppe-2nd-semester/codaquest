package com.example.codaquest.ui.components.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
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
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {

        TextField(value = loginViewModel.email,
            onValueChange = {loginViewModel.updateEmail(it)},
            label = { Text("Email")},
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondary,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
            ),
        )

        TextField(value = loginViewModel.password,
            onValueChange = {loginViewModel.updatePassword(it)},
            label = { Text("Password")},
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondary,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
            ),
        )

        Button(onClick = {
                loginViewModel.login(
                    navController = navController,
                    sharedViewModel = sharedViewModel,
                    loginViewModel = loginViewModel
                )
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Text(text = "Log In")
        }

        Text(text = loginViewModel.error)
    }
}

