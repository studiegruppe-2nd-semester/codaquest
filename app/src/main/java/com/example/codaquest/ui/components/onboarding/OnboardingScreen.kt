package com.example.codaquest.ui.components.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.codaquest.classes.OnboardingQuestionTypes

@Composable
fun OnboardingScreen (
    navController: NavController
) {
    val viewModel : OnboardingViewModel = viewModel()

    Column {

        when (viewModel.questions[viewModel.currentQuestion].type) {
            OnboardingQuestionTypes.RadioButton -> OnboardingRadioButtonComposable(viewModel = viewModel)
            OnboardingQuestionTypes.TextField -> OnboardingTextFieldComposable(viewModel = viewModel)
            OnboardingQuestionTypes.IntField -> OnboardingIntFieldComposable(viewModel = viewModel)
        }

        Button(onClick = { viewModel.nextQuestion(navController) }) {
            Text(viewModel.nextButton)
        }
    }
}
