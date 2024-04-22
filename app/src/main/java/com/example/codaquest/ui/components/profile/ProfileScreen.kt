package com.example.codaquest.ui.components.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.codaquest.R
import com.example.codaquest.ui.components.SharedViewModel
import com.example.codaquest.ui.components.navbar.NavBar
import com.example.codaquest.ui.components.project.ProjectComposable

/*
 TODO - IF NOT LOGGED IN AND YOU CLICK ON PROFILE FROM NAV BAR, IT SHOULD GO DIRECTLY TO LOGIN PAGE
 */

@Composable
fun ProfileScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    val viewModel : ProfileViewModel = viewModel()

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter) {

        Column(modifier = Modifier
            .padding(5.dp)
            .padding(bottom = 70.dp)
            .fillMaxSize()
        ) {
            Row(modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .fillMaxHeight(0.1f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
//                    text = "Settings" --- todo
                    text = ""
                )

                Text(modifier = Modifier
                    .clickable {
                        viewModel.logout(navController)
                    },
                    text = "Logout"
                )
            }

            Column(modifier = Modifier
                .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(id = R.drawable.profile_picture),
                    contentDescription = "profile picture",
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                        .size(300.dp)
                )

                (if (sharedViewModel.user?.username != null) sharedViewModel.user!!.username else "username not found")?.let {
                    Text(
                        text = it,
                        fontSize = 30.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(50.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Saved projects",
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 30.sp,
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(10.dp))
                
                if (viewModel.projects.isNotEmpty()) {
                    LazyColumn {
                        items(viewModel.projects) { item ->
                            ProjectComposable(project = item)
                        }
                    }
                }
                else {
                    Text(text = "No saved projects found")
                }
            }
        }

        NavBar(navController, sharedViewModel)

    }
}

//@Preview(showBackground = true)
//@Composable
//fun ProfileScreenPreview() {
//    CodaQuestTheme {
//        val sharedViewModel: SharedViewModel = viewModel()
//        ProfileScreen(rememberNavController(), sharedViewModel)
//    }
//}
