package com.example.codaquest.ui.components.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codaquest.domain.models.QuestionInfo

@Composable
fun StepDropdown(
    questionInfo: QuestionInfo,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
) {
    if (!questionInfo.options.isNullOrEmpty()) {
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

            DynamicDropdown(
                selectedValue = questionInfo.answer.value.ifEmpty { questionInfo.options[0] },
                expanded = expanded,
                onExpandedChange = { onExpandedChange(it) },
                options = questionInfo.options,
                label = "Level",
                onValueChangedEvent = { questionInfo.answer.value = it },
            )
        }
    }
}
