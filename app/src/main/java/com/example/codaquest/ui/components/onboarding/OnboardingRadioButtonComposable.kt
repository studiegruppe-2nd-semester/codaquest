package com.example.codaquest.ui.components.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.codaquest.ui.theme.CodaQuestTheme

@Composable
fun OnboardingRadioButtonComposable (
    viewModel : OnboardingViewModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(viewModel.questions[viewModel.currentQuestion].question)

        viewModel.questions[viewModel.currentQuestion].options?.forEach { option ->
            Button(
                onClick = { viewModel.questions[viewModel.currentQuestion].answer.value = option}
            ) {
                Text(text = option)
            }
        }

        Text(text = viewModel.questions[viewModel.currentQuestion].answer.value,
            fontSize = 30.sp
        )
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