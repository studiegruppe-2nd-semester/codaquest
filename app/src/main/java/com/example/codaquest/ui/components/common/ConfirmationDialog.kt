package com.example.codaquest.ui.components.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ConfirmationDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    dialogTitle: String,
    dialogMessage: String,
    dismissButtonMessage: String,
    confirmButtonMessage: String,
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(text = dialogTitle) },
            text = { Text(text = dialogMessage) },
            confirmButton = {
                Button(onClick = { onConfirm() }) {
                    Text(text = confirmButtonMessage)
                }
            },
            dismissButton = {
                Button(
                    onClick = onDismiss,
                ) {
                    Text(text = dismissButtonMessage)
                }
            },
        )
    }
}
