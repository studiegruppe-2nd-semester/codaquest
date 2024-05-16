package com.example.codaquest.ui.components.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.codaquest.ui.components.viewmodels.HomeScreenViewModel
import com.example.codaquest.ui.components.viewmodels.OnboardingViewModel
import com.example.codaquest.ui.theme.CodaQuestTheme


@Composable
fun ProjectIntFieldComposable(
    viewModel: HomeScreenViewModel,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            viewModel.questions[viewModel.currentQuestion].question,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            lineHeight = 25.sp,
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = viewModel.questions[viewModel.currentQuestion].answer.value,
            onValueChange = { viewModel.questions[viewModel.currentQuestion].answer.value = it },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondary,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
            ),
        )
    }
}


