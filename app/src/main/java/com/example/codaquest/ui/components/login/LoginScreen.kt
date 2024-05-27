package com.example.codaquest.ui.components.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.codaquest.domain.models.LoginState
import com.example.codaquest.ui.components.viewmodels.LoginViewModel
import com.example.codaquest.ui.components.viewmodels.SharedViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
) {
    val loginViewModel: LoginViewModel = viewModel()

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize(),
    ) {
        Button(
            onClick = { navController.popBackStack() },
            contentPadding = PaddingValues(),
            modifier = Modifier.size(40.dp),
        ) {
            Text(text = "X")
        }

        Spacer(modifier = Modifier.fillMaxHeight(0.1f))

        Box(modifier = Modifier.fillMaxHeight(0.6f)) {
            when (loginViewModel.state) {
                LoginState.Login -> LoginComposable(navController, sharedViewModel, loginViewModel)
                LoginState.Signup -> SignUpComposable(navController, sharedViewModel, loginViewModel)
            }
        }

        Spacer(modifier = Modifier.fillMaxHeight(0.1f))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Button(onClick = { loginViewModel.updateState() }) {
                Text(
                    text = loginViewModel.stateButtonText,
                    color = Color.Black,
                )
            }
        }
    }
}
