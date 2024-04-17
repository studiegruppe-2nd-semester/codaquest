package com.example.codaquest.ui.components.onboarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.codaquest.classes.OnboardingQuestionTypes
import com.example.codaquest.ui.theme.CodaQuestTheme

@Composable
fun OnboardingScreen (
    navController: NavController
) {
    val viewModel : OnboardingViewModel = viewModel()

    Column(modifier = Modifier.fillMaxSize()) {

        // PROGRESS BAR
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(modifier = Modifier
                .padding(horizontal = 5.dp)
                .fillMaxWidth(0.1f),
                onClick = { /*TODO*/ }
            ) {
                Text(text = "X")
            }
            LinearProgressIndicator(
                progress = { (viewModel.currentQuestion + 1).toFloat() / viewModel.questions.size.toFloat() },
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .fillMaxWidth(0.8f),
                color = Color.Red //progress color
            )
            Box(modifier = Modifier
                .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 5.dp),
                    text = "${viewModel.currentQuestion + 1}/${viewModel.questions.size}",
                )
            }
        }

        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.6f),
            contentAlignment = Alignment.Center
        ) {
            when (viewModel.questions[viewModel.currentQuestion].type) {
                OnboardingQuestionTypes.RadioButton -> OnboardingRadioButtonComposable(viewModel = viewModel)
                OnboardingQuestionTypes.TextField -> OnboardingTextFieldComposable(viewModel = viewModel)
                OnboardingQuestionTypes.IntField -> OnboardingIntFieldComposable(viewModel = viewModel)
            }
        }

        Row {

            Button(
                onClick = { viewModel.previousQuestion() },
                contentPadding = PaddingValues(0.dp)
            ) {
                Text("Previous")
            }

            Button(
                onClick = { viewModel.nextQuestion(navController) },
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(viewModel.nextButton)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    CodaQuestTheme {
        OnboardingScreen(rememberNavController())
    }
}
