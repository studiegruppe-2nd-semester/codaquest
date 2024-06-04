package com.example.codaquest.ui.components.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codaquest.domain.models.QuestionInfo

// Kasper og Ane
@Composable
fun StepTextField(
    questionInfo: QuestionInfo,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            questionInfo.question,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            lineHeight = 25.sp,
        )

        Spacer(modifier = Modifier.height(10.dp))

        CustomTextField(
            value = questionInfo.answer.value,
            onValueChange = { questionInfo.answer.value = it },
            label = null,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done,
        )
    }
}
