package com.example.codaquest.ui.components.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.codaquest.ui.components.common.CustomTextField
import com.example.codaquest.ui.components.common.LoadingDots
import com.example.codaquest.ui.components.viewmodels.LoginViewModel
import com.example.codaquest.ui.components.viewmodels.SharedViewModel

// Ane og Nathasja
@Composable
fun SignUpComposable(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    loginViewModel: LoginViewModel,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        CustomTextField(
            value = loginViewModel.loginInfo.username,
            onValueChange = { loginViewModel.loginInfo = loginViewModel.loginInfo.copy(username = it) },
            label = "Username",
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
        )

        CustomTextField(
            value = loginViewModel.loginInfo.email,
            onValueChange = { loginViewModel.loginInfo = loginViewModel.loginInfo.copy(email = it) },
            label = "Email",
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
        )

        CustomTextField(
            value = loginViewModel.loginInfo.password,
            onValueChange = { loginViewModel.loginInfo = loginViewModel.loginInfo.copy(password = it) },
            label = "Password",
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next,
        )

        CustomTextField(
            value = loginViewModel.loginInfo.confirmPassword,
            onValueChange = { loginViewModel.loginInfo = loginViewModel.loginInfo.copy(confirmPassword = it) },
            label = "Confirm password",
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
        )

        Button(
            onClick = {
                loginViewModel.signUp(onSuccess = {
                    loginViewModel.showError("")
                    sharedViewModel.changeUser(it)
                    navController.navigate("onboarding")
                })
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
            ),
        ) {
            Text("Sign Up")
        }

        Text(text = loginViewModel.error)

        if (loginViewModel.loading) {
            LoadingDots()
        }
    }
}
