package com.example.codaquest.domain.interfaces

import com.example.codaquest.domain.models.Project

interface ProjectDialogOperations {
    var showProjectDialog: Boolean
    var selectedDialogProject: Project?
}
