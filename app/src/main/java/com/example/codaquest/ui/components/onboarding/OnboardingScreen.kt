package com.example.codaquest.ui.components.onboarding

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun OnboardingScreen () {
    val viewModel : OnboardingViewModel = viewModel()
    Question1(viewModel = viewModel)
}