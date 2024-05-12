package com.example.codaquest.ui.components.savedProjects

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.codaquest.ui.components.SharedViewModel
import com.example.codaquest.ui.components.common.ProjectComposable
import com.example.codaquest.ui.components.common.TextTitle
import com.example.codaquest.ui.components.navbar.NavBar

// Ane
@Composable
fun SavedProjectsScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .padding(5.dp)
                .padding(bottom = 65.dp),
        ) {
            item {
                TextTitle(text = "Saved projects")

                Text(
                    text = amountOfProjectsSavedText(sharedViewModel.user?.projects!!.size),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(20.dp))
            }

            if (sharedViewModel.user?.projects?.isNotEmpty() == true) {
                items(sharedViewModel.user?.projects!!, key = { project ->
                    project.projectId.toString() // Use the projectId as a stable key
                }) { item ->
                    ProjectComposable(
                        project = item,
                        onDelete = { projectId ->
                            item.uid?.let { uid ->
                                sharedViewModel.deleteSavedProject(
                                    projectId,
                                    uid,
                                    "saved-projects",
                                )
                                if (sharedViewModel.loading) {
                                    navController.navigate("loading")
                                }
                            }
                        },
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                }
            } else {
                item {
                    Text(text = "No saved projects found")
                }
            }
        }
    }

    Box(contentAlignment = Alignment.BottomCenter) {
        NavBar("saved-projects", navController, sharedViewModel)
    }
}

fun amountOfProjectsSavedText(amount: Int): String {
    return if (amount == 1) {
        "$amount project saved"
    } else {
        "$amount projects saved"
    }
}