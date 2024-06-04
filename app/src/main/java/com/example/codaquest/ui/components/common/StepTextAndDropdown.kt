package com.example.codaquest.ui.components.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.codaquest.ui.components.viewmodels.GenerateProjectViewModel

// Ane
@Composable
fun StepTextFieldAndDropdown(
    generateProjectViewModel: GenerateProjectViewModel,
    options: List<String>,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            generateProjectViewModel.questions[generateProjectViewModel.currentQuestion].question,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            lineHeight = 25.sp,
        )

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f),
            contentAlignment = Alignment.Center,
        ) {
            DynamicDropdown(
                selectedValue = generateProjectViewModel.questions[generateProjectViewModel.currentQuestion].dropdownAnswer.value,
                expanded = generateProjectViewModel.dropdownExpanded,
                onExpandedChange = { generateProjectViewModel.dropdownExpanded = it },
                options = options,
                label = "",
                onValueChangedEvent = { generateProjectViewModel.questions[generateProjectViewModel.currentQuestion].dropdownAnswer.value = it },
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Or write custom answer")

        Spacer(modifier = Modifier.height(20.dp))

        CustomTextField(
            value = generateProjectViewModel.questions[generateProjectViewModel.currentQuestion].answer.value,
            onValueChange = { generateProjectViewModel.questions[generateProjectViewModel.currentQuestion].answer.value = it },
            label = null,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done,
        )
    }
}
