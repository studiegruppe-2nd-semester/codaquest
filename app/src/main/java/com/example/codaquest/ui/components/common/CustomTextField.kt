package com.example.codaquest.ui.components.common

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

// Ane
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String?,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
) {
    var visualTransformation: VisualTransformation = VisualTransformation.None
    if (keyboardType == KeyboardType.Password) {
        visualTransformation = PasswordVisualTransformation()
    }

    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = if (label != null) {
            { Text(label) }
        } else {
            null
        },
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction,
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.secondary,
            unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
        ),
    )
}
