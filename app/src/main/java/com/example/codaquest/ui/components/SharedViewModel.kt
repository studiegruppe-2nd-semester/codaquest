package com.example.codaquest.ui.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codaquest.classes.User
import com.example.codaquest.repositories.ProjectRepository
import com.example.codaquest.repositories.UserRepository
import com.example.codaquest.services.AccountService
import com.example.codaquest.services.ApiService
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
    var user: User? by mutableStateOf(null)
        private set

    fun changeUser(newUser: User?) {
        user = newUser
    }

    private fun fetchUserData() {
        val accountService = AccountService()
        val userUid = accountService.getCurrentUser()?.uid

        val userRepository = UserRepository()
        val projectRepository = ProjectRepository()
        userRepository.getKey(this)

        if (userUid !== null) {
            projectRepository.getProjects(userUid, this)
            userRepository.getUserData(userUid, null, this)
        }
    }


    fun promptApi() {
        val apiService = ApiService()
        apiService.initiateApi(this)

        viewModelScope.launch {
            apiService.promptApi()
        }

    }
}