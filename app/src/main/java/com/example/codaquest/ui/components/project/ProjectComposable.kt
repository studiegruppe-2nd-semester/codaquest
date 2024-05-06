package com.example.codaquest.ui.components.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codaquest.models.Project
import com.example.codaquest.ui.theme.CodaQuestTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProjectComposable(project: Project) {
    Column(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(20.dp))
            .background(color = MaterialTheme.colorScheme.secondary)
            .padding(15.dp, 10.dp),
    ) {
        project.title?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth(),
            )
        }

        FlowRow(
            modifier = Modifier
                .padding(vertical = 5.dp),
            verticalArrangement = Arrangement.spacedBy(7.dp),
        ) {
            val list = listOf(
                "Language: ${project.language}",
                "Length: ${project.length} hours",
                "Level: ${project.level}",
            )

            list.forEach { item ->
                Box(
                    modifier = Modifier
                        .padding(end = 5.dp)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(color = MaterialTheme.colorScheme.primary)
                        .padding(10.dp, 5.dp),

                ) {
                    Text(
                        text = item,
                        color = Color.Black,
                    )
                }
            }
        }

        project.description?.let {
            Text(
                text = it,
                modifier = Modifier.padding(vertical = 10.dp),
            )
        }

        Text(text = "Steps:", fontSize = 20.sp, modifier = Modifier.padding(vertical = 10.dp))
        project.steps?.forEach { steps ->
            Text(text = "- $steps\n", modifier = Modifier.padding(vertical = 5.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProjectComposablePreview() {
    CodaQuestTheme {
        ProjectComposable(
            Project(
                title = "Pizza Lover",
                keywords = "pizza",
                language = "Kotlin",
                length = 5,
                level = "Beginner",
                description = "This project is about pizza bla bla",
                steps = listOf(
                    "Step 1",
                    "step 2",
                    "step 3",
                ),
            ),
        )
    }
}
