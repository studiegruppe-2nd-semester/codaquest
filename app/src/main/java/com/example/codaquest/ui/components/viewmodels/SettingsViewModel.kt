package com.example.codaquest.ui.components.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.codaquest.domain.models.SettingsState

// Nathasja
class SettingsViewModel : ViewModel() {
    var settingsState: SettingsState by mutableStateOf(SettingsState.Overview)

    var showPasswordChange: Boolean by mutableStateOf(false)
    var showPersonalDetails: Boolean by mutableStateOf(false)

    // States for Password
    var newPassword: String by mutableStateOf("")
    var confirmNewPassword: String by mutableStateOf("")

    var passwordChangeTextField = mutableStateOf("")
        private set
}
