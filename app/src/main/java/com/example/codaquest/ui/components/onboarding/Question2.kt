package com.example.codaquest.ui.components.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.codaquest.ui.theme.CodaQuestTheme

@Composable
fun Question2 (
    viewModel : OnboardingViewModel
) {
    Column {
        Text("Which coding languages do you code in?")
        Text("Separate each language with a comma")

        TextField(
            value = viewModel.question2Answer,
            onValueChange = { viewModel.onQuestion2AnswerChange(it) }  )
    }
}

@Preview(showBackground = true)
@Composable
fun Greeting2Preview() {
    CodaQuestTheme {
        val viewModel : OnboardingViewModel = viewModel()
        Question2(viewModel = viewModel)
    }
}