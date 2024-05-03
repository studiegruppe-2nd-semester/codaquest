package com.example.codaquest.ui.components.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.codaquest.models.Project

class HomeScreenViewModel : ViewModel() {
    var error = ""

    var project: Project by mutableStateOf(
        Project(
            keywords = "",
            language = "",
            length = 0,
            level = "",
        ),
    )
}
