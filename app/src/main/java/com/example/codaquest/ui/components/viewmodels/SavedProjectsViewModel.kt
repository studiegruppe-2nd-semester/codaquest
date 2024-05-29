package com.example.codaquest.ui.components.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.codaquest.domain.interfaces.ProjectDialogOperations

// Ane
class SavedProjectsViewModel : ViewModel(), ProjectDialogOperations {
    override var showProjectDialog: Boolean by mutableStateOf(false)
}
