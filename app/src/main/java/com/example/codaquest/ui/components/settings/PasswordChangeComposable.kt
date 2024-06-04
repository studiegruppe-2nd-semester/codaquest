package com.example.codaquest.ui.components.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.codaquest.ui.components.common.CustomTextField
import com.example.codaquest.ui.components.viewmodels.SettingsViewModel

// Nathasja
@Composable
fun PasswordChangeComposable(
    settingsViewModel: SettingsViewModel,
    navController: NavController,
) {
    Text(
        text = "Change password",
        fontSize = 30.sp,
        color = Color(0xFF6BB38A),
        modifier = Modifier
            .padding(bottom = 10.dp),
    )
    Spacer(modifier = Modifier.height(15.dp))

    if (!settingsViewModel.reAuthenticate) {
        CustomTextField(
            value = settingsViewModel.newPassword,
            onValueChange = { newValue ->
                settingsViewModel.newPassword = newValue
            },
            label = "Enter New Password",
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next,
        )

        Spacer(modifier = Modifier.height(15.dp))

        CustomTextField(
            value = settingsViewModel.confirmNewPassword,
            onValueChange = { newValue ->
                settingsViewModel.confirmNewPassword = newValue
            },
            label = "Confirm New Password",
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            settingsViewModel.reAuthenticate()
        }) {
            Text(text = "Change Password")
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(15.dp),
        ) {
            Text(text = "Please login again to confirm the change of password")

            CustomTextField(
                value = settingsViewModel.loginInfo.email,
                onValueChange = { settingsViewModel.loginInfo = settingsViewModel.loginInfo.copy(email = it) },
                label = "Email",
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
            )

            CustomTextField(
                value = settingsViewModel.loginInfo.password,
                onValueChange = { settingsViewModel.loginInfo = settingsViewModel.loginInfo.copy(password = it) },
                label = "Password",
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
            )

            Button(
                onClick = {
                    settingsViewModel.updatePassword(
                        onSuccess = { navController.navigate("profile") },
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                ),
            ) {
                Text(text = "Re-authenticate")
            }
        }
    }
    if (settingsViewModel.error.isNotEmpty()) {
        Text(text = settingsViewModel.error)
    }
}
