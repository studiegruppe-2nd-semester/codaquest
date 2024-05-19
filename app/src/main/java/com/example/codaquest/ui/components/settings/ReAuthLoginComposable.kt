package com.example.codaquest.ui.components.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.example.codaquest.ui.components.viewmodels.LoginViewModel
import com.example.codaquest.ui.components.viewmodels.SharedViewModel

@Composable
fun ReAuthLoginComposable(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    loginViewModel: LoginViewModel,
    onReAuthSucces: () -> Unit,
) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        Text(text = "Please login again to delete your account")

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
            imeAction = ImeAction.Done,
        )

        Button(
            onClick = {
                loginViewModel.login(
                    onSuccess = {
                        sharedViewModel.changeUser(it)
                        loginViewModel.showError("")
                        onReAuthSucces()
                    },
                )
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
            ),
        ) {
            Text(text = "Login")
        }
    }
}
