package com.example.codaquest.ui.components.project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.codaquest.classes.Project
import com.example.codaquest.ui.theme.CodaQuestTheme

@Composable
fun ProjectComposable(project: Project) {
    Column {
        Text(text = project.title)

        Row {
            Text(text = "Language: ${project.language}")
            Text(text = "Length: ${project.length} hours")
            Text(text = "Level: ${project.level}")
        }

        Text(text = project.description)

        Text(text = "Requirements:")
        project.requirements.forEach { requirement ->
            Text(text = "- $requirement")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProjectComposablePreview() {
    CodaQuestTheme {
        ProjectComposable(
            Project(
            title = "project 1",
            language = "Kotlin",
            length = 5,
            level = "Beginner",
            description = "description text bla bla bla",
            requirements = listOf(
                "Step 1",
                "Step 2",
                "Step 3",
                "Step 4",
            )
        )
        )
    }
}
