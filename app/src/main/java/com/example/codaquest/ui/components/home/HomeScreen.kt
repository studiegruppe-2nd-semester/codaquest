package com.example.codaquest.ui.components.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.codaquest.ui.components.common.ProjectDialog
import com.example.codaquest.ui.components.common.ProjectOverviewComposable
import com.example.codaquest.ui.components.navbar.NavBar
import com.example.codaquest.ui.components.viewmodels.HomeScreenViewModel
import com.example.codaquest.ui.components.viewmodels.SharedViewModel

// Kasper og Ane
@Composable
fun HomeScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
) {
    val homeScreenViewModel: HomeScreenViewModel = viewModel()

    LazyColumn(
        modifier = Modifier
            .padding(5.dp)
            .padding(bottom = 65.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        item {
            Text(
                text = "Project generator",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
                    .wrapContentSize(Alignment.Center)
                    .padding(top = 20.dp, bottom = 20.dp),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                color = Color(0xFF6BB38A),
                lineHeight = 45.sp,
            )
            Text(
                text = "Here you can generate a project by answering some questions...Then we do the rest with help from chat-gpt",
                modifier = Modifier.padding(horizontal = 16.dp),
                textAlign = TextAlign.Center,
            )

            Spacer(
                modifier = Modifier
                    .padding(top = 30.dp),
            )

            Button(onClick = { navController.navigate("generate-project") }) {
                Text(text = "Start generating a new project")
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (sharedViewModel.lastGeneratedProject != null) {
                Text(
                    text = "Most recent generated project",
                    fontSize = 20.sp,
                )

                sharedViewModel.lastGeneratedProject?.let { project ->
                    ProjectOverviewComposable(
                        uid = sharedViewModel.user?.userUid,
                        project = project,
                        onOpenDialog = {
                            homeScreenViewModel.selectedDialogProject = project
                            homeScreenViewModel.showProjectDialog = true
                        },
                        onSaveClick = {
                            sharedViewModel.user?.userUid?.let { uid ->
                                sharedViewModel.saveProject(uid, project)
                                navController.navigate("saved-projects")
                            }
                        },
                    )
                }
            }
        }
    }

    if (homeScreenViewModel.showProjectDialog && homeScreenViewModel.selectedDialogProject != null) {
        ProjectDialog(
            project = homeScreenViewModel.selectedDialogProject!!,
            onDismissDialog = { homeScreenViewModel.showProjectDialog = false },
            uid = sharedViewModel.user?.userUid,
            onSaveClick = { project ->
                sharedViewModel.user?.userUid?.let { uid ->
                    sharedViewModel.saveProject(uid, project)
                    navController.navigate("saved-projects")
                }
            },
        )
    }

    Box(contentAlignment = Alignment.BottomCenter) {
        NavBar("home", navController, sharedViewModel)
    }
}
