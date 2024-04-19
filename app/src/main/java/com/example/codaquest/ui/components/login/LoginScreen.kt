package com.example.codaquest.ui.components.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.codaquest.classes.LoginState
import com.example.codaquest.ui.components.SharedViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    val loginViewModel: LoginViewModel = viewModel()

    Column(modifier = Modifier
        .padding(5.dp)
        .fillMaxSize()
    ) {

        Button(onClick = { navController.navigate("home") }) {
            Text(text = "X")
        }

        Spacer(modifier = Modifier.fillMaxHeight(0.2f))

        Box(modifier = Modifier.fillMaxHeight(0.5f)) {
            when (loginViewModel.state) {
                LoginState.login -> LoginComposable(navController, sharedViewModel, loginViewModel)
                LoginState.signup -> SignUpComposable(navController, sharedViewModel, loginViewModel)
            }
        }

        Spacer(modifier = Modifier.fillMaxHeight(0.1f))

        Box(modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = { loginViewModel.updateState() }) {
                Text(text = loginViewModel.stateButtonText)
            }
        }

    }
}