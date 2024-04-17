package com.example.codaquest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.codaquest.ui.components.home.HomeScreen
import com.example.codaquest.ui.components.onboarding.OnboardingScreen

@Composable
fun SetupNavHost (
    navController : NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "home" ) {
        composable("home") {
            HomeScreen(navController = navController)
        }

        composable("onboarding"){
            OnboardingScreen(navController = navController)
        }
    }
}