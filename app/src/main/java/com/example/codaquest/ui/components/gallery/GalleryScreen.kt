package com.example.codaquest.ui.components.gallery

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import com.example.codaquest.domain.models.Filters
import com.example.codaquest.domain.models.LevelType
import com.example.codaquest.ui.components.common.CustomTextField
import com.example.codaquest.ui.components.common.DynamicDropdown
import com.example.codaquest.ui.components.common.ProjectOverviewComposable
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
    if (sharedViewModel.galleryProjects != null) {
        galleryViewModel.filteredGalleryProjects = sharedViewModel.galleryProjects!!
    }

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
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    if (galleryViewModel.showFilters) {
                        CustomTextField(
                            value = galleryViewModel.filters.searchText,
                            onValueChange = {
                                galleryViewModel.updateFilters(
                                    galleryViewModel.filters.copy(
                                        searchText = it,
                                    ),
                                )
                            },
                            label = "Search keywords",
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next,
                        )

                        CustomTextField(
                            value = galleryViewModel.filters.language,
                            onValueChange = {
                                galleryViewModel.updateFilters(
                                    galleryViewModel.filters.copy(
                                        language = it,
                                    ),
                                )
                            },
                            label = "Coding language",
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next,
                        )

                        CustomTextField(
                            value = galleryViewModel.filters.length,
                            onValueChange = {
                                galleryViewModel.updateFilters(
                                    galleryViewModel.filters.copy(
                                        length = it,
                                    ),
                                )
                            },
                            label = "Project length (hours)",
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done,
                        )

                        DynamicDropdown(
                            selectedValue = galleryViewModel.filters.level,
                            expanded = galleryViewModel.filters.levelExpanded,
                            onExpandedChange = {
                                galleryViewModel.updateFilters(
                                    galleryViewModel.filters.copy(
                                        levelExpanded = it,
                                    ),
                                )
                            },
                            options = listOf(
                                "Choose level",
                                LevelType.Beginner.toString(),
                                LevelType.Intermediate.toString(),
                                LevelType.Advanced.toString(),
                            ),
                            label = "Level",
                            onValueChangedEvent = {
                                galleryViewModel.updateFilters(
                                    galleryViewModel.filters.copy(level = it),
                                )
                            },
                        )

                        Box(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center,
                        ) {
                            Button(onClick = {
                                galleryViewModel.filterProjects(sharedViewModel.galleryProjects!!)
                                galleryViewModel.showFilters = false
                            }) {
                                Text(text = "Search")
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.CenterStart,
                            ) {
                                Text(
                                    text = "Clear",
                                    fontSize = 15.sp,
                                    modifier = Modifier.clickable {
                                        galleryViewModel.updateFilters(Filters())
                                        galleryViewModel.filteredGalleryProjects =
                                            sharedViewModel.galleryProjects!!
                                    },
                                )
                            }
                        }
                    }
                }
                Text(
                    text = "${galleryViewModel.filteredGalleryProjects.size} projects shown",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = galleryViewModel.error,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(20.dp))
            }

            if (galleryViewModel.filteredGalleryProjects.isNotEmpty()) {
                items(galleryViewModel.filteredGalleryProjects, key = { project ->
                    project.projectId.toString() // Use the projectId as a stable key
                }) { project ->
                    ProjectOverviewComposable(
                        uid = sharedViewModel.user?.userUid,
                        project = project,
                        showProjectDialog = galleryViewModel.showProjectDialog,
                        onDismissDialog = { galleryViewModel.showProjectDialog = false },
                        onOpenDialog = { galleryViewModel.showProjectDialog = true },
                        onSaveClick = {
                            sharedViewModel.user?.userUid?.let { uid ->
                                sharedViewModel.saveProject(uid, project)
                                navController.navigate("saved-projects")
                            }
                        },
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }

    Box(contentAlignment = Alignment.BottomCenter) {
        NavBar("gallery", navController, sharedViewModel)
    }
}
