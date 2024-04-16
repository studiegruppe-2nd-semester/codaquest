package com.example.codaquest.ui.components.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.codaquest.ui.theme.CodaQuestTheme

@Composable
fun OnboardingTextFieldComposable (
    viewModel : OnboardingViewModel
) {
    Column {
        Text(viewModel.questions[viewModel.currentQuestion].question)
//        Text("Separate each language with a comma")

        TextField(
            value = viewModel.questions[viewModel.currentQuestion].answer.value,
            onValueChange = { viewModel.questions[viewModel.currentQuestion].answer.value = it })
    }
}

@Preview(showBackground = true)
@Composable
fun Greeting2Preview() {
    CodaQuestTheme {
        val viewModel : OnboardingViewModel = viewModel()
        OnboardingTextFieldComposable(viewModel = viewModel)
    }
}