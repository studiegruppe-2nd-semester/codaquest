package com.example.codaquest.ui.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codaquest.classes.User
import com.example.codaquest.repositories.UserRepository
import com.example.codaquest.services.AccountService
import com.example.codaquest.services.ApiService
import com.example.codaquest.ui.components.home.HomeScreenViewModel
import kotlinx.coroutines.launch

class SharedViewModel: ViewModel() {
    var key: String? = null
        private set
    fun updateKey(newKey: String) {
        key = newKey
    }


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
            userRepository.getKey(this)
            userRepository.getUserData(userUid, null, sharedViewModel = this)
        }
    }


    fun promptApi(homeScreenViewModel: HomeScreenViewModel) {
        val apiService = ApiService()
        apiService.initiateApi(this)

        viewModelScope.launch {
            apiService.promptApi(homeScreenViewModel)
        }

    }
}