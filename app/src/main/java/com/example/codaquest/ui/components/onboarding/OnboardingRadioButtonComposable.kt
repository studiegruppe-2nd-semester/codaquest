package com.example.codaquest.ui.components.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.codaquest.ui.theme.CodaQuestTheme

@Composable
fun OnboardingRadioButtonComposable(viewModel: OnboardingViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            modifier = Modifier.padding(vertical = 20.dp),
            text = viewModel.questions[viewModel.currentQuestion].question,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            lineHeight = 25.sp,
        )

        viewModel.questions[viewModel.currentQuestion].options?.forEach { option ->
            Button(
                modifier = Modifier.padding(vertical = 5.dp),
                onClick = { viewModel.questions[viewModel.currentQuestion].answer.value = option },
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor = viewModel.addButtonColor(option),
                        contentColor = Color.Black,
                    ),
            ) {
                Text(
                    text = option,
                    fontSize = 20.sp,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CodaQuestTheme {
        val viewModel: OnboardingViewModel = viewModel()
        OnboardingRadioButtonComposable(viewModel = viewModel)
    }
}
