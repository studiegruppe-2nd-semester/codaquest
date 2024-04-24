package com.example.codaquest.ui.components.profile

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.codaquest.classes.Project
import com.example.codaquest.services.AccountService

class ProfileViewModel: ViewModel() {
    private val accountService = AccountService()

    fun logout(navController: NavController) {
        accountService.logout(navController = navController)
    }

    val projects: List<Project> = listOf(
        Project(
            title = "Pizza Lover",
            keywords = "pizza",
            language = "Kotlin",
            length = 5,
            level = "Beginner",
            description = "This project is about pizza bla bla",
            steps = mutableListOf(
                "Step 1",
                "step 2",
                "step 3"
            )
        ),
        Project(
            title = "UBER TRUCKS",
            keywords = "Uber for trucks",
            language = "Kotlin",
            length = 5,
            level = "Beginner",
            description = "This project is about making a uber for trucks bla bla",
            steps = mutableListOf(
                "step 1",
                "step 2",
                "step 3"
            )
        ),
    )
}