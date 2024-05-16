package com.example.codaquest.ui.components.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codaquest.data.repositories.GalleryRepository
import com.example.codaquest.data.repositories.ProjectRepository
import com.example.codaquest.data.repositories.UserRepository
import com.example.codaquest.data.services.AccountService
import com.example.codaquest.domain.models.Project
import com.example.codaquest.domain.models.User
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {
    init {
        fetchUserData()
        getGalleryProjects()
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

    // ------------------------------------- GALLERY PROJECTS
    var galleryProjects: List<Project>? by mutableStateOf(null)

    private fun getGalleryProjects() {
        viewModelScope.launch {
            val galleryRepository = GalleryRepository()

            galleryRepository.fetchGalleryProjects(
                onSuccess = { fetchedProjects ->
                    galleryProjects = fetchedProjects
                },
            )
        }
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

            userRepository.getKey(onSuccess = { fetchedKey ->
                if (fetchedKey != null) {
                    key = fetchedKey
                }
            })

            if (userUid !== null) {
                userRepository.getUserData(userUid, onSuccess = { user ->
                    changeUser(user)
                })
            }
        }
    }

    // ------------------------------------- GENERATED PROJECT
    var lastGeneratedProject: Project? by mutableStateOf(null)

    fun saveProject(
        uid: String,
        project: Project,
    ) {
        projectRepository.saveUserProject(
            project.copy(uid = uid),
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
