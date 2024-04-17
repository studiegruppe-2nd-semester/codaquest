package com.example.codaquest.ui.components.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.codaquest.ui.theme.CodaQuestTheme

@Composable
fun OnboardingRadioButtonComposable (
    viewModel : OnboardingViewModel
) {
    Column {
        Text(viewModel.questions[viewModel.currentQuestion].question)

        viewModel.questions[viewModel.currentQuestion].options?.forEach { option ->
            Button(
                onClick = { viewModel.questions[viewModel.currentQuestion].answer.value = option},
                colors = ButtonDefaults.buttonColors(
                    containerColor = viewModel.addButtonColor(option),
                    contentColor = Color.Black
                )
            ) {
                Text(text = option)
            }
        }

        Text(text = viewModel.questions[viewModel.currentQuestion].answer.value)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CodaQuestTheme {
        val viewModel : OnboardingViewModel = viewModel()
        OnboardingRadioButtonComposable(viewModel = viewModel)
    }
}