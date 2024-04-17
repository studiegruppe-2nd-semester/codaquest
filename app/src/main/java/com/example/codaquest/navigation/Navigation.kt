package com.example.codaquest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.codaquest.ui.components.home.HomeScreen
import com.example.codaquest.ui.components.onboarding.OnboardingScreen
import com.example.codaquest.ui.components.profile.ProfileScreen

@Composable
fun SetupNavHost (
    navController : NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "onboarding" ) {

        composable("home") {
            HomeScreen(navController = navController)
        }

        composable("onboarding"){
            OnboardingScreen(navController = navController)
        }

        composable("profile"){
            ProfileScreen(navController = navController)
        }
    }
}