package com.example.codaquest.ui.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codaquest.Models.Project
import com.example.codaquest.Models.User
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

    private val projectRepository : ProjectRepository = ProjectRepository()

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
        this.user = newUser
        println("New user: $user")
    }

    fun addProject(project: Project) {
        if (user?.projects != null) {
            user?.projects!!.add(project)
            changeUser(user)
        }
        else {
            changeUser(user?.copy(projects = mutableListOf(project)))
        }
    }


    private fun fetchUserData() {
        println("Trying to fetch user data")
        viewModelScope.launch {
            val accountService = AccountService()
            val userUid = accountService.getCurrentUser()?.uid
//            println("useruid: $userUid")

            val userRepository = UserRepository()

            userRepository.getKey(onSuccess = {
                if (it != null) {
                    updateKey(it)
                }
            })


            if (userUid !== null) {
                userRepository.getUserData(userUid, onSuccess = { user ->
                    changeUser(user)
                })
            }
        }
    }

    fun promptApi(
        projectInfo: Project,
        onSuccess: (Project) -> Unit
    ) {
        val apiService = ApiService()
        apiService.initiateApi(this)

        viewModelScope.launch {
            apiService.promptApi(projectInfo, onSuccess = { onSuccess(it) })
        }

    }

    // ------------------------------------- GENERATED PROJECT
    var project by mutableStateOf(Project())

    fun addProject (
        uid: String,
        onSuccess: (Project) -> Unit
    ) {
        projectRepository.addProject(
            project.copy(uid = uid),
            onSuccess = { project -> onSuccess(project) }
        )
    }
}