package com.example.codaquest.ui.components.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.codaquest.R
import com.example.codaquest.models.Project
import com.example.codaquest.ui.components.SharedViewModel
import com.example.codaquest.ui.components.navbar.NavBar

@Composable
fun ProfileScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
) {
    val viewModel: ProfileViewModel = viewModel()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .padding(5.dp)
                .padding(bottom = 65.dp),
//            .fillMaxSize()
        ) {
            item {
                Row(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                        .height(45.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_settings),
                        contentDescription = "settings icon",
                    )

                    Image(
                        painter = painterResource(id = R.drawable.ic_logout),
                        contentDescription = "settings icon",
                        modifier = Modifier
                            .clickable {
                                viewModel.logout(
                                    onSuccess = {
                                        sharedViewModel.changeUser(null)
                                        sharedViewModel.project = Project()
                                        navController.navigate("home")
                                    },
                                )
                            },
                    )
//                    Text(
//                        modifier = Modifier
//                            .clickable {
//                                viewModel.logout(
//                                    onSuccess = {
//                                        sharedViewModel.changeUser(null)
//                                        sharedViewModel.project = Project()
//                                        navController.navigate("home")
//                                    },
//                                )
//                            },
//                        text = "Logout",
//                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile_picture),
                        contentDescription = "profile picture",
                        modifier = Modifier
                            .padding(bottom = 20.dp)
                            .size(300.dp),
                    )

                    (if (sharedViewModel.user?.username != null) sharedViewModel.user!!.username else "username not found")?.let {
                        Text(
                            text = it,
                            fontSize = 30.sp,
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }
                }

                Spacer(modifier = Modifier.height(50.dp))

                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Onboarding answers",
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 30.sp,
                        modifier = Modifier.fillMaxWidth(),
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // TODO Onboarding answers
                }
            }
        }
    }

    Box(contentAlignment = Alignment.BottomCenter) {
        NavBar("profile", navController, sharedViewModel)
    }
}
