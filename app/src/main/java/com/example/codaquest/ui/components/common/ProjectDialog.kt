package com.example.codaquest.ui.components.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.codaquest.domain.models.Project

// Ane
@Composable
fun ProjectDialog(
    project: Project,
    onDelete: ((String) -> Unit?)? = null,
    onDismissDialog: () -> Unit,
    uid: String?,
    onSaveClick: ((Project) -> Unit)? = null,
) {
    Dialog(
        onDismissRequest = { onDismissDialog() },
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
        ),
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            item {
                ProjectComposable(
                    project = project,
                    onDismissDialog = onDismissDialog,
                    onDelete = onDelete,
                    uid = uid,
                    onSaveClick = onSaveClick,
                )
            }
        }
    }
}
