package com.example.codaquest.ui.components.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codaquest.domain.models.QuestionInfo

// Kasper og Ane
@Composable
fun StepRadioButtons(
    questionInfo: QuestionInfo,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            modifier = Modifier.padding(vertical = 20.dp),
            text = questionInfo.question,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            lineHeight = 25.sp,
        )

        questionInfo.options?.forEach { option ->
            Button(
                modifier = Modifier.padding(vertical = 5.dp),
                onClick = { questionInfo.answer.value = option },
                colors = ButtonDefaults.buttonColors(
                    containerColor = addButtonColor(option, questionInfo.answer.value),
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

@Composable
fun addButtonColor(answer: String, currentQuestionAnswer: String): Color {
    return if (answer == currentQuestionAnswer) {
        MaterialTheme.colorScheme.tertiary
    } else {
        MaterialTheme.colorScheme.secondary
    }
}
