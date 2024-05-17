package com.example.codaquest.ui.components.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.codaquest.ui.components.common.StepDropdown
import com.example.codaquest.ui.components.common.StepIntField
import com.example.codaquest.ui.components.common.StepRadioButtons
import com.example.codaquest.ui.components.common.StepTextField
import com.example.codaquest.ui.components.viewmodels.OnboardingViewModel
import com.example.codaquest.ui.components.viewmodels.SharedViewModel

@Composable
fun OnboardingScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
) {
    val onboardingViewModel: OnboardingViewModel = viewModel()

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
                onClick = { navController.navigate("profile") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                ),
            ) {
                Text(text = "X")
            }

            LinearProgressIndicator(
                progress = { (onboardingViewModel.currentQuestion + 1).toFloat() / onboardingViewModel.questions.size.toFloat() },
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
                    text = "${onboardingViewModel.currentQuestion + 1}/${onboardingViewModel.questions.size}",
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
            contentAlignment = Alignment.Center,
        ) {
            when (onboardingViewModel.questions[onboardingViewModel.currentQuestion].type) {
                QuestionTypes.RadioButton -> StepRadioButtons(
                    questionInfo = onboardingViewModel.questions[onboardingViewModel.currentQuestion],
                )

                QuestionTypes.TextField -> StepTextField(
                    questionInfo = onboardingViewModel.questions[onboardingViewModel.currentQuestion],
                )

                QuestionTypes.IntField -> StepIntField(
                    questionInfo = onboardingViewModel.questions[onboardingViewModel.currentQuestion],
                )

                QuestionTypes.Dropdown -> StepDropdown(
                    questionInfo = onboardingViewModel.questions[onboardingViewModel.currentQuestion],
                    expanded = onboardingViewModel.dropdownExpanded,
                    onExpandedChange = { onboardingViewModel.dropdownExpanded = it },
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(
                enabled = onboardingViewModel.currentQuestion != 0,
                modifier = Modifier.padding(5.dp),
                onClick = { onboardingViewModel.previousQuestion() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (onboardingViewModel.currentQuestion == 0) MaterialTheme.colorScheme.inversePrimary else MaterialTheme.colorScheme.primary,
                ),
            ) {
                Text("Previous")
            }

            Button(
                modifier = Modifier.padding(5.dp),
                onClick = {
                    sharedViewModel.user?.let { user ->
                        onboardingViewModel.nextQuestion(
                            user,
                            onSuccess = {
                                sharedViewModel.changeUser(it)
                                navController.navigate("profile")
                            },
                        )
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
            ) {
                Text(onboardingViewModel.nextButton)
            }
        }
    }
}
