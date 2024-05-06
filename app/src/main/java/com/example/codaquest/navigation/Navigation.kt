package com.example.codaquest.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.codaquest.ui.components.SharedViewModel
import com.example.codaquest.ui.components.home.HomeScreen
import com.example.codaquest.ui.components.login.LoginScreen
import com.example.codaquest.ui.components.onboarding.OnboardingScreen
import com.example.codaquest.ui.components.profile.ProfileScreen

@Composable
fun SetupNavHost(
    navController: NavHostController,
) {
    val sharedViewModel: SharedViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "profile",
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
    }
}
