package com.example.codaquest.ui.components.gallery

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.codaquest.domain.models.LevelType
import com.example.codaquest.domain.models.stringToLevelType
import com.example.codaquest.ui.components.common.CustomTextField
import com.example.codaquest.ui.components.common.DynamicDropdown
import com.example.codaquest.ui.components.common.ProjectPreview
import com.example.codaquest.ui.components.common.TextTitle
import com.example.codaquest.ui.components.navbar.NavBar
import com.example.codaquest.ui.components.viewmodels.GalleryViewModel
import com.example.codaquest.ui.components.viewmodels.SharedViewModel

@Composable
fun GalleryScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
) {
    val galleryViewModel: GalleryViewModel = viewModel()
    galleryViewModel.filteredGalleryProjects = sharedViewModel.galleryProjects!!

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .padding(5.dp)
                .padding(bottom = 65.dp),
        ) {
            item {
                TextTitle(text = "Project gallery")

                Text(
                    text = "Filters",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable {
                        galleryViewModel.showFilters = !galleryViewModel.showFilters
                    },
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.secondary),
                ) {
                    if (galleryViewModel.showFilters) {
                        CustomTextField(
                            value = "",
                            onValueChange = {},
                            label = "Search keywords",
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next,
                        )
                        DynamicDropdown(
                            selectedValue = galleryViewModel.filters.level.toString(),
                            expanded = galleryViewModel.filters.levelExpanded,
                            onExpandedChange = { galleryViewModel.updateFilters(galleryViewModel.filters.copy(levelExpanded = it)) },
                            options = listOf(LevelType.Beginner.toString(), LevelType.Intermediate.toString(), LevelType.Advanced.toString()),
                            label = "Level",
                            onValueChangedEvent = { galleryViewModel.updateFilters(galleryViewModel.filters.copy(level = stringToLevelType(it))) },
                        )

                        Box(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center,
                        ) {
                            Button(onClick = { galleryViewModel.filterProjects(sharedViewModel.galleryProjects!!) }) {
                                Text(text = "Search")
                            }
                        }
                    }
                }

                Text(
                    text = "${galleryViewModel.filteredGalleryProjects.size} projects shown",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(20.dp))
            }

            if (galleryViewModel.filteredGalleryProjects.isNotEmpty()) {
                items(galleryViewModel.filteredGalleryProjects, key = { project ->
                    project.projectId.toString() // Use the projectId as a stable key
                }) { item ->
                    ProjectPreview(
                        uid = sharedViewModel.user?.userUid,
                        project = item,
                        onSaveClick = {
                            sharedViewModel.user?.userUid?.let { uid ->
                                sharedViewModel.saveProject(uid)
                                navController.navigate("saved-projects")
                            }
                        },
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                }
            } else {
                item {
                    Text(text = "No projects found in the gallery")
                }
            }
        }
    }

    Box(contentAlignment = Alignment.BottomCenter) {
        NavBar("gallery", navController, sharedViewModel)
    }
}
