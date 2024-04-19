package com.example.codaquest.ui.components.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.codaquest.ui.components.navbar.NavBar
import com.example.codaquest.ui.components.project.ProjectComposable
import com.example.codaquest.ui.theme.CodaQuestTheme

/*
 TODO - IF NOT LOGGED IN AND YOU CLICK ON PROFILE FROM NAV BAR, IT SHOULD GO DIRECTLY TO LOGIN PAGE
 */

@Composable
fun ProfileScreen(
    navController: NavController
) {
    val viewModel : ProfileViewModel = viewModel()

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter) {

        Column(modifier = Modifier
            .padding(5.dp)
            .fillMaxSize()
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.1f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Settings")
                Text(text = "Logout")
            }

            Column(modifier = Modifier
                .padding(bottom = 30.dp)
                .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier
                    .padding(bottom = 20.dp)
                    .background(Color.LightGray)
                    .size(300.dp)
                ) {

                }
                Text(
                    text = "Username",
                    fontSize = 30.sp
                )
            }

            Column {
                Text(
                    text = "Saved projects",
                    fontSize = 30.sp
                )

                LazyColumn {
                    items(viewModel.projects) { item ->
                        ProjectComposable(project = item)
                    }
                }
            }
        }

        NavBar(navController)

    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    CodaQuestTheme {
        ProfileScreen(rememberNavController())
    }
}
