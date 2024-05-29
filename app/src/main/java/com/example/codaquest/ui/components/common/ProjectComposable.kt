package com.example.codaquest.ui.components.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.codaquest.R
import com.example.codaquest.domain.models.Project
import com.example.codaquest.ui.components.viewmodels.ConfirmationDialogViewModel

// Aaron og Ane
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProjectComposable(
    project: Project,
    onDismissDialog: (() -> Unit?)? = null,
    onDelete: ((String) -> Unit?)? = null,
    uid: String? = null,
    onSaveClick: ((Project) -> Unit)? = null,
) {
    val confirmationDialogViewModel: ConfirmationDialogViewModel = viewModel()

    SelectionContainer {
        Column(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(20.dp))
                .background(color = MaterialTheme.colorScheme.secondary)
                .padding(15.dp, 10.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp),
        ) {
            Column {
                if (onDismissDialog != null || onDelete != null) {
                    Row(
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Box(
                            modifier = Modifier
                                .size(30.dp),
                        ) {
                            if (onDismissDialog != null) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_back_arrow),
                                    contentDescription = "back arrow icon",
                                    modifier = Modifier.clickable {
                                        onDismissDialog()
                                    },
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .size(30.dp),
                        ) {
                            if (onDelete != null) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_trashcan),
                                    contentDescription = "trashcan icon",
                                    modifier = Modifier.clickable {
                                        confirmationDialogViewModel.showConfirmationDialog = true
                                    },
                                )
                            }
                        }
                    }
                }

                project.title?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                            .fillMaxWidth(),
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

            Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                Text(
                    text = "Steps:",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(vertical = 10.dp),
                )
                project.steps?.forEach { steps ->
                    Text(text = "- $steps\n")
                }
            }

            if (uid != null && onSaveClick != null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center,
                ) {
                    Button(
                        onClick = {
                            onSaveClick(project)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.tertiary,
                            contentColor = Color.White,
                        ),
                    ) {
                        Text(text = "Save project")
                    }
                }
            }

            // Aaron
            if (confirmationDialogViewModel.showConfirmationDialog) {
                ConfirmationDialog(
                    showDialog = confirmationDialogViewModel.showConfirmationDialog,
                    onDismiss = { confirmationDialogViewModel.showConfirmationDialog = false },
                    onConfirm = {
                        if (onDelete != null) {
                            project.projectId?.let { onDelete(it) }
                        }
                        confirmationDialogViewModel.showConfirmationDialog = false
                    },
                    dialogTitle = "Delete Project",
                    dialogMessage = "Are you sure you want to delete the project?",
                    dismissButtonMessage = "Cancel",
                    confirmButtonMessage = "Delete",
                )
            }
        }
    }
}
