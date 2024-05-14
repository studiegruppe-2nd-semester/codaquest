package com.example.codaquest.ui.components.navbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.codaquest.R
import com.example.codaquest.domain.models.User
import com.example.codaquest.ui.components.common.NavBarOption
import com.example.codaquest.ui.components.viewmodels.SharedViewModel

@Composable
fun NavBar(
    currentScreen: String,
    navController: NavController,
    sharedViewModel: SharedViewModel,
) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.secondary)
            .fillMaxWidth()
            .height(65.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            NavBarOption(
                contentDescription = "gallery icon",
                getIcon("gallery", currentScreen),
                onClick = { navController.navigate("gallery") },
            )

            NavBarOption(
                contentDescription = "home icon",
                getIcon("home", currentScreen),
                onClick = { navController.navigate("home") },
            )

            NavBarOption(
                contentDescription = "bookmark icon",
                getIcon("saved-projects", currentScreen),
                onClick = { navController.navigate("saved-projects") },
            )

            NavBarOption(
                contentDescription = "${profileOrLogin(sharedViewModel.user)} icon",
                getIcon(profileOrLogin(sharedViewModel.user), currentScreen),
                onClick = { navController.navigate(profileOrLogin(sharedViewModel.user)) },
            )
        }
    }
}

fun profileOrLogin(user: User?): String {
    return if (user != null) {
        "profile"
    } else {
        "login"
    }
}

fun getIcon(screen: String, currentScreen: String): Int {
    if (currentScreen == screen) {
        return when (screen) {
            "home" -> R.drawable.ic_home_selected
            "profile" -> R.drawable.ic_profile_selected
            "login" -> R.drawable.ic_profile_selected
            "saved-projects" -> R.drawable.ic_bookmark_selected
            "gallery" -> R.drawable.ic_bookmark_selected
            else -> R.drawable.ic_reload
        }
    } else {
        return when (screen) {
            "home" -> R.drawable.ic_home
            "profile" -> R.drawable.ic_profile
            "login" -> R.drawable.ic_profile
            "saved-projects" -> R.drawable.ic_bookmark
            "gallery" -> R.drawable.ic_bookmark
            else -> R.drawable.ic_reload
        }
    }
}
