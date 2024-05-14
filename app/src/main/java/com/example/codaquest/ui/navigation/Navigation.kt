package com.example.codaquest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.codaquest.ui.components.gallery.GalleryScreen
import com.example.codaquest.ui.components.home.HomeScreen
import com.example.codaquest.ui.components.loading.LoadingScreen
import com.example.codaquest.ui.components.login.LoginScreen
import com.example.codaquest.ui.components.onboarding.OnboardingScreen
import com.example.codaquest.ui.components.profile.ProfileScreen
import com.example.codaquest.ui.components.savedProjects.SavedProjectsScreen
import com.example.codaquest.ui.components.viewmodels.SharedViewModel

@Composable
fun SetupNavHost(
    navController: NavHostController,
) {
    val sharedViewModel: SharedViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "home",
    ) {
        composable("home") {
            HomeScreen(
                navController = navController,
                sharedViewModel = sharedViewModel,
            )
        }

        composable("login") {
            LoginScreen(
                navController = navController,
                sharedViewModel = sharedViewModel,
            )
        }

        composable("onboarding") {
            OnboardingScreen(
                navController = navController,
                sharedViewModel = sharedViewModel,
            )
        }

        composable("profile") {
            ProfileScreen(
                navController = navController,
                sharedViewModel = sharedViewModel,
            )
        }

        composable("saved-projects") {
            SavedProjectsScreen(
                navController = navController,
                sharedViewModel = sharedViewModel,
            )
        }

        composable("loading") {
            LoadingScreen(
                navController = navController,
                sharedViewModel = sharedViewModel,
            )
        }

        composable("gallery") {
            GalleryScreen(
                navController = navController,
                sharedViewModel = sharedViewModel,
            )
        }
    }
}
