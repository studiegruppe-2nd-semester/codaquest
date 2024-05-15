package com.example.codaquest.ui.components.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SettingsViewmodel : ViewModel() {
    val showPasswordChange = mutableStateOf(false)
    val showPersonalDetails = mutableStateOf(false)

    var passwordChangeTextField = mutableStateOf("")
        private set

    fun onPasswordChangeClick() {
        showPasswordChange.value = !showPasswordChange.value
    }
}
