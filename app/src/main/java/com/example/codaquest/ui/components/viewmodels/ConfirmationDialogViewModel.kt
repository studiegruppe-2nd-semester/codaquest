package com.example.codaquest.ui.components.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ConfirmationDialogViewModel : ViewModel() {
    var showConfirmationDialog: Boolean by mutableStateOf(false)
}
