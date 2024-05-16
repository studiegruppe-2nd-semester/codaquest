package com.example.codaquest.ui.components.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codaquest.ui.components.viewmodels.HomeScreenViewModel

@Composable
fun ProjectFirstQuestion(
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
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondary,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
            ),
        )
    }
}

/*
@Composable
fun ProjectSecondQuestion(
    viewModel: HomeScreenViewModel
) {
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

        viewModel.questions[viewModel.currentQuestion].options?.let { level ->
            Button(
                modifier = Modifier.padding(vertical = 5.dp),
                onClick = { viewModel.questions[viewModel.currentQuestion].answer.value = level.toString()},
                colors = ButtonDefaults.buttonColors(
                    containerColor = viewModel.addButtonColor(level.toString()),
                    contentColor = Color.Black,
                ),
            ) {
                Text(
                    text = level.toString(),
                    fontSize = 20.sp,
                )
            }
        }
    }
} */
