package com.example.codaquest.ui.components.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.codaquest.domain.interfaces.ErrorOperations
import com.example.codaquest.domain.interfaces.ProjectDialogOperations
import com.example.codaquest.domain.models.Filters
import com.example.codaquest.domain.models.Project
import com.example.codaquest.domain.models.stringToLevelType

class GalleryViewModel : ViewModel(), ErrorOperations, ProjectDialogOperations {
    var showFilters: Boolean by mutableStateOf(false)

    var filters: Filters by mutableStateOf(Filters())

    fun updateFilters(filters: Filters) {
        this.filters = filters
    }

    var filteredGalleryProjects: List<Project> by mutableStateOf(listOf())

    fun filterProjects(
        galleryProjects: List<Project>,
    ) {
        filteredGalleryProjects = galleryProjects.filter { project ->
            val searchTextMatch = if (filters.searchText.isEmpty()) {
                true
            } else if (project.title?.contains(filters.searchText, ignoreCase = true) == true) {
                true
            } else if (project.description?.contains(filters.searchText, ignoreCase = true) == true) {
                true
            } else {
                false
            }

            val languageMatch = if (filters.language.isEmpty()) {
                true
            } else if (project.language?.lowercase() == filters.language.lowercase()) {
                true
            } else if (project.language?.lowercase()?.contains(filters.language.lowercase()) == true) {
                true
            } else {
                false
            }

            val lengthMatch = if (filters.length.isEmpty()) {
                true
            } else if (project.length == filters.length.toIntOrNull()) {
                true
            } else {
                false
            }

            val levelMatch = if (filters.level == "Choose level") {
                true
            } else if (project.level == stringToLevelType(filters.level)) {
                true // No level filter specified, so consider all levels
            } else {
                false
            }

            searchTextMatch && languageMatch && lengthMatch && levelMatch
        }.sortedBy { it.title }

        if (filteredGalleryProjects.isEmpty()) {
            showError("Please clear the filters")
        } else if (error.isNotEmpty()) {
            showError("")
        }
    }

    override var showProjectDialog: Boolean by mutableStateOf(false)

    override var selectedDialogProject: Project? by mutableStateOf(null)

    override var error: String = ""
    override fun showError(error: String) {
        this.error = error
    }
}
