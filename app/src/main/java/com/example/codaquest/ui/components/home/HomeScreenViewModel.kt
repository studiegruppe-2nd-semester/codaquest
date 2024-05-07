package com.example.codaquest.ui.components.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.codaquest.models.GenerateProjectDetails


class HomeScreenViewModel : ViewModel() {
    var generateProjectDetails: GenerateProjectDetails by mutableStateOf(GenerateProjectDetails())

}
