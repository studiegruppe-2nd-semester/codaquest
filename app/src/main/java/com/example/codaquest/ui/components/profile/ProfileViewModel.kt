package com.example.codaquest.ui.components.profile

import androidx.lifecycle.ViewModel
import com.example.codaquest.classes.Project

class ProfileViewModel: ViewModel() {
    val projects: List<Project> = listOf(
        Project(
            title = "project 1",
            language = "Kotlin",
            length = 5,
            level = "Beginner",
            description = "description text bla bla bla",
            requirements = listOf(
                "Step 1",
                "Step 2",
                "Step 3",
                "Step 4",
            )
        ),
        Project(
            title = "project 2",
            language = "Kotlin",
            length = 3,
            level = "Intermediate",
            description = "description text bla bla bla",
            requirements = listOf(
                "Step 1",
                "Step 2",
                "Step 3",
                "Step 4",
            )
        ),
    )
}