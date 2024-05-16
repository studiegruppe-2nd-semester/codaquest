package com.example.codaquest.ui.components.settings

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.codaquest.data.services.AccountService
import com.example.codaquest.ui.components.common.CustomTextField
import com.example.codaquest.ui.components.viewmodels.SettingsViewModel

@Composable
fun PasswordChangeComposable(
    settingsViewModel: SettingsViewModel,
    accountService: AccountService,
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
        val newPassword = settingsViewModel.newPassword
        val confirmPassword = settingsViewModel.confirmNewPassword

        if (newPassword == confirmPassword) {
            accountService.updatePassword(newPassword) { result ->
                navController.navigate("Settings")
            }
        } else {
            println("Error")
        }
    }) {
        Text(text = "Change Password")
    }
}
