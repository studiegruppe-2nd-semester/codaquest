package com.example.codaquest.ui.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codaquest.models.GenerateProjectDetails
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

    var loading: Boolean = false
    var loadingRoute: String? = null

    // ------------------------------------- KEY
    var key: String? = null

    // ------------------------------------- USER
    var user: User? by mutableStateOf(null)
        private set
    fun changeUser(newUser: User?) {
        this.user = newUser
        println("New user: $user")
    }

    private fun saveProjectInViewModel(project: Project) {
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
        projectInfo: GenerateProjectDetails,
        onError: (String) -> Unit,
    ) {
        val apiService = ApiService()
        apiService.initiateApi(this)

        viewModelScope.launch {
            apiService.generateProjectSuggestion(
                projectInfo,
                onSuccess = { generatedProject = it },
                onError = { error -> onError(error) },
            )
        }
    }

    // ------------------------------------- GENERATED PROJECT
    var generatedProject by mutableStateOf(Project())

    fun saveProject(
        uid: String,
    ) {
        projectRepository.saveUserProject(
            generatedProject.copy(uid = uid),
            onSuccess = { saveProjectInViewModel(it) },
        )
    }

    fun deleteSavedProject(
        projectId: String,
        uid: String,
        loadingRoute: String,
    ) {
        if (uid == user?.userUid) {
            this.loading = true
            this.loadingRoute = loadingRoute

            viewModelScope.launch {
                projectRepository.deleteSavedProject(
                    projectId,
                    onSuccess = {
                        val projects: MutableList<Project>? = user!!.projects
                        projects?.removeIf { it.projectId == projectId }
                        changeUser(user!!.copy(projects = projects))

                        loading = false
                    },
                )
            }
        }
    }
}
