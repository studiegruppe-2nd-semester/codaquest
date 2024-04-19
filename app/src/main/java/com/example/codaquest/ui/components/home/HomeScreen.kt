package com.example.codaquest.ui.components.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.codaquest.ui.components.SharedViewModel

@Composable
fun HomeScreen (navController: NavController, sharedViewModel: SharedViewModel) {
    // So we can see it the right screen delete the text if necessary
    Text(text = "HomeScreen")
}