package com.example.codaquest.ui.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codaquest.models.Project
import com.example.codaquest.models.User
import com.example.codaquest.repositories.ProjectRepository
import com.example.codaquest.repositories.UserRepository
import com.example.codaquest.services.AccountService
import com.example.codaquest.services.ApiService
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {
    init {
        fetchUserData()
    }

    private val projectRepository: ProjectRepository = ProjectRepository()

    // ------------------------------------- KEY
    var key: String? = null

    // ------------------------------------- USER
    var user: User? by mutableStateOf(null)
        private set
    fun changeUser(newUser: User?) {
        this.user = newUser
        println("New user: $user")
    }


    fun saveProjectInViewModel(project: Project) {
        if (user?.projects != null) {
            user?.projects!!.add(project)
            changeUser(user)
        } else {
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
                    key = it
                }
            })

            if (userUid !== null) {
                userRepository.getUserData(userUid, onSuccess = { user ->
                    changeUser(user)
                })
            }
        }
    }

    fun getProjectSuggestion(
        projectInfo: Project,
        onSuccess: (Project) -> Unit,
    ) {
        val apiService = ApiService()
        apiService.initiateApi(this)

        viewModelScope.launch {
            apiService.generateProjectSuggestion(projectInfo, onSuccess = { onSuccess(it) })
        }
    }

    // ------------------------------------- GENERATED PROJECT
    var project by mutableStateOf(Project())

    fun saveProject(
        uid: String,
        onSuccess: (Project) -> Unit,
    ) {
        projectRepository.saveUserProject(
            project.copy(uid = uid),
            onSuccess = { project -> onSuccess(project) },
        )
    }
}
