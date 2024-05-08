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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.codaquest.R
import com.example.codaquest.models.User
import com.example.codaquest.ui.components.SharedViewModel
import com.example.codaquest.ui.components.common.NavBarOption

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
                backgroundColor = chooseBackgroundColor(screen = "home", currentScreen = currentScreen),
                contentDescription = "home icon",
                getIcon("home"),
                onClick = { navController.navigate("home") },
            )

            NavBarOption(
                backgroundColor = chooseBackgroundColor(screen = "profile", currentScreen = currentScreen),
                contentDescription = "${profileOrLogin(sharedViewModel.user)} icon",
                getIcon(profileOrLogin(sharedViewModel.user)),
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

fun getIcon(name: String): Int {
    return when (name) {
        "home" -> R.drawable.ic_home
        "profile" -> R.drawable.ic_profile
        "login" -> R.drawable.ic_profile
        else -> R.drawable.ic_reload
    }
}

@Composable
fun chooseBackgroundColor(screen: String, currentScreen: String): Color {
    return if (currentScreen == screen) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.secondary
    }
}
