package com.example.codaquest.ui.components.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.codaquest.domain.interfaces.ErrorOperations
import com.example.codaquest.domain.models.Filters
import com.example.codaquest.domain.models.Project

class GalleryViewModel : ViewModel(), ErrorOperations {
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
            /* TODO */
            project.title?.isNotEmpty() ?: false
        }
    }

    override var error: String = ""
    override fun showError(error: String) {
        this.error = error
    }
}
