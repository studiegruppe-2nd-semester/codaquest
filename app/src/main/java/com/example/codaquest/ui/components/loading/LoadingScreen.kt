package com.example.codaquest.ui.components.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.codaquest.ui.components.viewmodels.SharedViewModel

@Composable
fun LoadingScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "LOADING")
    }

    when (sharedViewModel.loading) {
        false -> navController.navigate(sharedViewModel.loadingRoute ?: "home")
        else -> {}
    }
}
