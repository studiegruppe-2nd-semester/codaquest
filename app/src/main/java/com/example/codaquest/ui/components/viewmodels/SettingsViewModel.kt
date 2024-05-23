package com.example.codaquest.ui.components.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codaquest.data.services.AccountService
import com.example.codaquest.domain.interfaces.ErrorOperations
import com.example.codaquest.domain.models.LoginInfo
import com.example.codaquest.domain.models.SettingsState
import kotlinx.coroutines.launch

// Nathasja
class SettingsViewModel() : ViewModel(), ErrorOperations {
    var settingsState: SettingsState by mutableStateOf(SettingsState.Overview)

    // State to show pop up dialog
    var showPopUpDialog by mutableStateOf(false)
    var showReLoginPopUp by mutableStateOf(false)

    // States for Password
    var newPassword: String by mutableStateOf("")
    var confirmNewPassword: String by mutableStateOf("")

    // Error states
    override var error by mutableStateOf("")

    override fun showError(error: String) {
        this.error = error
    }

    var reAuthenticate: Boolean by mutableStateOf(false)
    var loginInfo: LoginInfo by mutableStateOf(LoginInfo())

    private val accountService = AccountService()

    fun reAuthenticate() {
        if (newPassword == confirmNewPassword) {
            showError("")
            reAuthenticate = true
        } else {
            showError("Passwords do not match")
        }
    }
    fun updatePassword(
        onSuccess: () -> Unit,
    ) {
        viewModelScope.launch {
            accountService.updatePassword(loginInfo, newPassword) { success, message ->
                showError(message)
                if (success) {
                    println("Password  updated successfully")
                    showError("")
                    onSuccess()
                } else {
                    showError("Failed to update password\nPlease try again")
                    println("Failed to update password: $message")
                }
            }
        }
    }

    fun deleteAccount(onCompleted: () -> Unit) {
        viewModelScope.launch {
            accountService.deleteAccount(
                loginInfo,
                onCompleted = onCompleted,
                onError = { showError(it) },
            )
        }
    }
}
