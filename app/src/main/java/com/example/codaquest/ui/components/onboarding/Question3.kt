package com.example.codaquest.ui.components.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.codaquest.ui.theme.CodaQuestTheme

@Composable
fun Question3 (
    viewModel : OnboardingViewModel
) {
    Column {
        Text("At what level are you in coding?")

        Button(onClick = { viewModel.question1Answer = "Beginner"}) {
            Text(text = "Beginner")
        }

        Button(onClick = { viewModel.question1Answer = "Intermediate" }) {
            Text(text = "Intermediate")
        }

        Button(onClick = { viewModel.question1Answer = "Advanced" }) {
            Text(text = "Advanced")
        }

        viewModel.question1Answer?.let { Text(text = it) }
    }
}

@Preview(showBackground = true)
@Composable
fun Greeting3Preview() {
    CodaQuestTheme {
        val viewModel : OnboardingViewModel = viewModel()
        Question3(viewModel = viewModel)
    }
}