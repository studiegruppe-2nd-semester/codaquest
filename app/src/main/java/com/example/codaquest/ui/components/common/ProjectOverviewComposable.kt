package com.example.codaquest.ui.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.codaquest.domain.models.LevelType
import com.example.codaquest.domain.models.Project
import com.example.codaquest.ui.theme.CodaQuestTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProjectOverviewComposable(
    uid: String?,
    project: Project,
    onSaveClick: (Project) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(20.dp))
            .background(color = MaterialTheme.colorScheme.secondary)
            .padding(15.dp, 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            project.title?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }

        FlowRow(
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

        if (uid != null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                Button(
                    onClick = { onSaveClick(project) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = Color.White,
                    ),
                ) {
                    Text(text = "Save project")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProjectComposablePreviewPreview() {
    CodaQuestTheme {
        ProjectOverviewComposable(
            uid = null,
            Project(
                title = "Pizza Lover",
                language = "Kotlin",
                length = 5,
                level = LevelType.Beginner,
                description = "This project is about pizza bla bla",
                steps = listOf(
                    "Step 1",
                    "step 2",
                    "step 3",
                ),
            ),
            onSaveClick = {},
        )
    }
}
