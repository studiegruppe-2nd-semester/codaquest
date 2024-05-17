package com.example.codaquest.ui.components.generateProject

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.codaquest.domain.models.QuestionTypes
import com.example.codaquest.ui.components.common.ProjectComposable
import com.example.codaquest.ui.components.common.StepIntField
import com.example.codaquest.ui.components.common.StepRadioButtons
import com.example.codaquest.ui.components.common.StepTextField
import com.example.codaquest.ui.components.viewmodels.GenerateProjectViewModel
import com.example.codaquest.ui.components.viewmodels.SharedViewModel

// Kasper
@Composable
fun GenerateProjectScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
) {
    val generateProjectViewModel: GenerateProjectViewModel = viewModel()

    Column(
        modifier = Modifier
            .padding(0.5.dp)
            .fillMaxSize(),
    ) {
        // PROGRESS BAR
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.1f),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Button(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .fillMaxWidth(0.1f),
                contentPadding = PaddingValues(0.dp),
                onClick = { navController.navigate("home") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                ),
            ) {
                Text(text = "X")
            }

            LinearProgressIndicator(
                progress = { (generateProjectViewModel.currentQuestion + 1).toFloat() / generateProjectViewModel.questions.size.toFloat() },
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .fillMaxWidth(0.8f),
                color = MaterialTheme.colorScheme.primary, // progress color
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 5.dp),
                    text = "${generateProjectViewModel.currentQuestion + 1}/${generateProjectViewModel.questions.size}",
                )
            }
        }

        if (generateProjectViewModel.showGeneratedProject) {
            LazyColumn {
                item {
                    sharedViewModel.lastGeneratedProject?.let { ProjectComposable(project = it, deletable = false, onDelete = {}) }

                    if (sharedViewModel.user?.userUid != null) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center,
                        ) {
                            Button(
                                onClick = {
                                    sharedViewModel.user?.userUid?.let { uid ->
                                        sharedViewModel.lastGeneratedProject?.let { project ->
                                            sharedViewModel.saveProject(uid, project)
                                        }
                                        navController.navigate("saved-projects")
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.tertiary,
                                    contentColor = Color.White,
                                ),
                            ) {
                                Text(text = "Save project")
                            }
                        }
                    }
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f),
                contentAlignment = Alignment.Center,
            ) {
                when (generateProjectViewModel.questions[generateProjectViewModel.currentQuestion].type) {
                    QuestionTypes.TextField -> StepTextField(
                        questionInfo = generateProjectViewModel.questions[generateProjectViewModel.currentQuestion],
                    )
                    QuestionTypes.RadioButton -> StepRadioButtons(
                        questionInfo = generateProjectViewModel.questions[generateProjectViewModel.currentQuestion],
                    )
                    QuestionTypes.IntField -> StepIntField(
                        questionInfo = generateProjectViewModel.questions[generateProjectViewModel.currentQuestion],
                    )
                }
            }

            Text(text = generateProjectViewModel.error)

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Button(
                    modifier = Modifier.padding(5.dp),
                    onClick = { generateProjectViewModel.previousQuestion() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (generateProjectViewModel.currentQuestion == 0) MaterialTheme.colorScheme.inversePrimary else MaterialTheme.colorScheme.primary,
                    ),
                ) {
                    Text("Previous")
                }

                // After answering last question, the button should generate project instead of going to next question
                if ((generateProjectViewModel.currentQuestion == generateProjectViewModel.questions.size - 1)) {
                    Button(
                        enabled = generateProjectViewModel.questions[generateProjectViewModel.currentQuestion].answer.value.isNotEmpty(),
                        modifier = Modifier.padding(5.dp),
                        onClick = {
                            sharedViewModel.key?.let {
                                generateProjectViewModel.getProjectSuggestion(
                                    key = it,
                                    onSuccess = {
                                            project ->
                                        sharedViewModel.lastGeneratedProject = project
                                        generateProjectViewModel.showGeneratedProject = true
                                    },
                                )
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                        ),
                    ) {
                        Text("Generate project")
                    }
                } else {
                    Button(
                        modifier = Modifier.padding(5.dp),
                        onClick = {
                            generateProjectViewModel.nextQuestion()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                        ),
                    ) {
                        Text("Next")
                    }
                }
            }
        }
    }
}
