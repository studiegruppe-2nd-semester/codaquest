package com.example.codaquest.ui.components.login

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.codaquest.classes.LoginState

@Composable
fun LoginScreen() {
    val loginViewModel: LoginViewModel = viewModel()

    when (loginViewModel.state) {
        LoginState.login -> println()
        LoginState.signup -> SignUpComposable()
    }

}