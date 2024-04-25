package com.example.codaquest.ui.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codaquest.classes.Project
import com.example.codaquest.classes.User
import com.example.codaquest.interfaces.UserOperations
import com.example.codaquest.repositories.ProjectRepository
import com.example.codaquest.repositories.UserRepository
import com.example.codaquest.services.AccountService
import com.example.codaquest.services.ApiService
import kotlinx.coroutines.launch

class SharedViewModel: ViewModel(), UserOperations {
    init {
        fetchUserData()
    }

//    val navigateToProfile = mutableStateOf(false)

    // ------------------------------------- KEY
    var key: String? = null
        private set
    override fun updateKey(newKey: String) {
        key = newKey
    }

    // ------------------------------------- USER
    var user: User? by mutableStateOf(null)
        private set
    override fun changeUser(newUser: User?) {
        this.user = user
    }


    private fun fetchUserData() {
        viewModelScope.launch {
            val accountService = AccountService()
            val userUid = accountService.getCurrentUser()?.uid

            val userRepository = UserRepository()
            val projectRepository = ProjectRepository()
            userRepository.getKey(this@SharedViewModel)

            if (userUid !== null) {
                projectRepository.getProjects(userUid, this@SharedViewModel)
                userRepository.getUserData(userUid, onSuccess = { changeUser(it) })
            }
        }
    }

    fun promptApi(projectInfo: Project) {
        val apiService = ApiService()
        apiService.initiateApi(this)

        viewModelScope.launch {
            apiService.promptApi(projectInfo)
        }

    }
}