package com.example.codaquest.ui.components.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.codaquest.ui.theme.CodaQuestTheme

@Composable
fun OnboardingIntFieldComposable (
    viewModel : OnboardingViewModel
) {
    Column {
        Text(viewModel.questions[viewModel.currentQuestion].question)

        TextField(
            value = viewModel.questions[viewModel.currentQuestion].answer.value,
            onValueChange = { viewModel.questions[viewModel.currentQuestion].answer.value = it },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number))
    }
}

@Preview(showBackground = true)
@Composable
fun Greeting3Preview() {
    CodaQuestTheme {
        val viewModel : OnboardingViewModel = viewModel()
        OnboardingIntFieldComposable(viewModel = viewModel)
    }
}