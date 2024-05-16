package com.example.codaquest.domain.models

import com.example.codaquest.ui.components.viewmodels.HomeScreenViewModel
import com.example.codaquest.ui.components.viewmodels.SharedViewModel

data class GenerateProjectDetails(
    var keywords: String = "",
    var language: String = "",
    val length: Int = 0,
    val level: LevelType = LevelType.Beginner,
)


