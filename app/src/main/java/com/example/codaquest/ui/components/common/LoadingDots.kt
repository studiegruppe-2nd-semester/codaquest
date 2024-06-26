package com.example.codaquest.ui.components.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.codaquest.ui.components.viewmodels.LoadingViewModel

// Ane
@Composable
fun LoadingDots() {
    val loadingViewModel: LoadingViewModel = viewModel()

    Box(
        modifier = Modifier
            .height(20.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = loadingViewModel.loadingText)
    }
}
