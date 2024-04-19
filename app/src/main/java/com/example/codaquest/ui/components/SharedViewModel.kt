package com.example.codaquest.ui.components

import androidx.lifecycle.ViewModel
import com.example.codaquest.classes.User
import com.example.codaquest.repositories.UserRepository
import com.example.codaquest.services.AccountService

class SharedViewModel: ViewModel() {
    init {
        fetchUserData()
    }
    var user: User? = null
        private set

    fun changeUser(newUser: User?) {
        user = newUser
    }

    private fun fetchUserData() {
        val accountService = AccountService()
        val userUid = accountService.getCurrentUser()?.uid

        if (userUid !== null) {
            val userRepository = UserRepository()
            userRepository.getUserData(userUid, null, sharedViewModel = this)
        }
    }
}