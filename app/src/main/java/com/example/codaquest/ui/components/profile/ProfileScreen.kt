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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.codaquest.R
import com.example.codaquest.domain.models.LevelType
import com.example.codaquest.domain.models.OnboardingData
import com.example.codaquest.domain.models.Project
import com.example.codaquest.ui.components.common.ConfirmationDialog
import com.example.codaquest.ui.components.common.CustomTextField
import com.example.codaquest.ui.components.common.DynamicDropdown
import com.example.codaquest.ui.components.navbar.NavBar
import com.example.codaquest.ui.components.viewmodels.ProfileViewModel
import com.example.codaquest.ui.components.viewmodels.SharedViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
) {
    val profileViewModel: ProfileViewModel = viewModel()

    sharedViewModel.user?.onboardingData?.let {
        addOnboardingDataToProfileViewModel(
            it,
            profileViewModel,
        )
    }

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
                        contentDescription = "logout icon",
                        modifier = Modifier
                            .clickable {
                                profileViewModel.logout(
                                    onSuccess = {
                                        profileViewModel.showConfirmationDialog = true
                                    },
                                )
                            },
                    )
                }

                ConfirmationDialog(
                    showDialog = profileViewModel.showConfirmationDialog,
                    onDismiss = { profileViewModel.showConfirmationDialog = false },
                    onConfirm = {
                        profileViewModel.logout(
                            onSuccess = {
                                sharedViewModel.changeUser(null)
                                sharedViewModel.lastGeneratedProject = Project()
                                navController.navigate("home")
                                profileViewModel.showConfirmationDialog = false
                            },
                        )
                    },
                    dialogTitle = "Log out",
                    dialogMessage = "Are you sure you want to log out?",
                    dismissButtonMessage = "Cancel",
                    confirmButtonMessage = "Log out",
                )

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

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(35.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        Text(
                            text = "Onboarding answers",
                            fontSize = 25.sp,
                        )

                        Image(
                            painter = painterResource(id = R.drawable.ic_edit),
                            contentDescription = "pencil icon",
                            modifier = Modifier.clickable {
                                profileViewModel.toggleEditingOnboardingAnswers()
                            },
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Ane
                    profileViewModel.onboardingAnswerTitles.forEach { answer ->

                        Text(text = answer.value)

                        when (profileViewModel.editingOnboardingAnswers) {
                            true -> {
                                if (answer.key == "level") {
                                    Box(modifier = Modifier.fillMaxWidth(0.8f)) {
                                        DynamicDropdown(
                                            selectedValue = profileViewModel.level,
                                            expanded = profileViewModel.onboardingLevelExpanded,
                                            onExpandedChange = { profileViewModel.onboardingLevelExpanded = it },
                                            options = LevelType.entries.map { it.toString() },
                                            label = "",
                                            onValueChangedEvent = { newValue ->
                                                profileViewModel.editOnboardingAnswers(answer.key, newValue)
                                            },
                                        )
                                    }
                                } else {
                                    CustomTextField(
                                        value = when (answer.key) {
                                            "languages" -> profileViewModel.languages
                                            "project-length" -> profileViewModel.projectLength
                                            else -> ""
                                        },
                                        onValueChange = { newValue ->
                                            profileViewModel.editOnboardingAnswers(answer.key, newValue)
                                        },
                                        label = "",
                                        keyboardType = when (answer.key) {
                                            "languages" -> KeyboardType.Text
                                            "project-length" -> KeyboardType.Number
                                            else -> KeyboardType.Text
                                        },
                                        imeAction = ImeAction.Done,
                                    )
                                }
                            }

                            false -> Text(text = findOnboardingData(answer.key, sharedViewModel.user?.onboardingData))
                        }

                        Spacer(modifier = Modifier.height(20.dp))
                    }

                    if (profileViewModel.editingOnboardingAnswers) {
                        Button(onClick = {
                            sharedViewModel.user?.let { user ->
                                profileViewModel.saveOnboardingAnswers(
                                    user,
                                    onSuccess = { updatedUser ->
                                        sharedViewModel.changeUser(updatedUser)
                                        profileViewModel.editingOnboardingAnswers = false
                                    },
                                )
                            }
                        }) {
                            Text(text = "Save changes")
                        }
                    }
                }
            }
        }
    }

    Box(contentAlignment = Alignment.BottomCenter) {
        NavBar("profile", navController, sharedViewModel)
    }
}

fun findOnboardingData(name: String, onboardingData: OnboardingData?): String {
    return when (name) {
        "level" -> "${onboardingData?.level ?: "No saved data"}"
        "languages" -> onboardingData?.languages ?: "No saved data"
        "project-length" -> "${onboardingData?.projectLength ?: "No saved data"}"
        else -> "No saved data"
    }
}

fun addOnboardingDataToProfileViewModel(onboardingData: OnboardingData, profileViewModel: ProfileViewModel) {
    profileViewModel.level = (onboardingData.level ?: "").toString()
    profileViewModel.languages = onboardingData.languages ?: ""
    profileViewModel.projectLength = (onboardingData.projectLength ?: "").toString()
}
