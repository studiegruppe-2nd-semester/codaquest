package com.example.codaquest.ui.components.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.codaquest.classes.OnboardingQuestionTypes

@Composable
fun OnboardingScreen () {
    val viewModel : OnboardingViewModel = viewModel()

    Column {
//        when (viewModel.currentQuestion) {
//            0 -> Question1(viewModel = viewModel)
//            1 -> Question2(viewModel = viewModel)
//            2 -> Question3(viewModel = viewModel)
//            // Add more questions as needed
//        }

        when (viewModel.questions[viewModel.currentQuestion].type) {
            OnboardingQuestionTypes.RadioButton -> OnboardingRadioButtonComposable(viewModel = viewModel)
            OnboardingQuestionTypes.TextField -> OnboardingTextFieldComposable(viewModel = viewModel)
            OnboardingQuestionTypes.IntField -> OnboardingIntFieldComposable(viewModel = viewModel)
        }

        Button(onClick = { viewModel.nextQuestion() }) {
            Text(viewModel.nextButton)
        }
    }
}
