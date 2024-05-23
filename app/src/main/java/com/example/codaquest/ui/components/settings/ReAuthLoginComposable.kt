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
import com.example.codaquest.domain.models.LoginInfo
import com.example.codaquest.ui.components.common.CustomTextField

@Composable
fun ReAuthLoginComposable(
    explanationText: String,
    loginInfo: LoginInfo,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onReAuthSuccess: () -> Unit,
) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        Text(text = explanationText)

        CustomTextField(
            value = loginInfo.email,
            onValueChange = { onEmailChange(it) },
            label = "Email",
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
        )

        CustomTextField(
            value = loginInfo.password,
            onValueChange = { onPasswordChange(it) },
            label = "Password",
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
        )

        Button(
            onClick = {
                onReAuthSuccess()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
            ),
        ) {
            Text(text = "Login")
        }
    }
}
