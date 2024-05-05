package com.example.codaquest.ui.components.profile

import androidx.lifecycle.ViewModel
import com.example.codaquest.services.AccountService

class ProfileViewModel : ViewModel() {
    private val accountService = AccountService()

    fun logout(
        onSuccess: () -> Unit,
    ) {
        accountService.logout(onSuccess = { onSuccess() })
    }
}
