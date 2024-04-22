package com.example.codaquest.ui.components.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.codaquest.ui.components.SharedViewModel
import com.example.codaquest.ui.components.navbar.NavBar

@Composable
fun HomeScreen (
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    // So we can see it the right screen delete the text if necessary

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {

        Column(modifier = Modifier
            .padding(5.dp)
            .fillMaxSize()
        ) {
            Text(text = "HomeScreen")
            // your code starts here!



            // Your code ends here
        }

        NavBar(navController, sharedViewModel)
    }
}