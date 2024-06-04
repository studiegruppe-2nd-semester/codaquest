package com.example.codaquest.ui.components.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.codaquest.domain.interfaces.ProjectDialogOperations
import com.example.codaquest.domain.models.Project

// Ane
class HomeScreenViewModel : ViewModel(), ProjectDialogOperations {
    override var showProjectDialog: Boolean by mutableStateOf(false)

    override var selectedDialogProject: Project? by mutableStateOf(null)
}
