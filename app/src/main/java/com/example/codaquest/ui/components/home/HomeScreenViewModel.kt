package com.example.codaquest.ui.components.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.codaquest.interfaces.ErrorOperations
import com.example.codaquest.models.GenerateProjectDetails

class HomeScreenViewModel : ViewModel(), ErrorOperations {
    var generateProjectDetails: GenerateProjectDetails by mutableStateOf(GenerateProjectDetails())

    // __________________________ DROPDOWN MENU //Skal først bruges når jeg har checket med et normalt checkfelt angående enum class...
    var isExpanded by mutableStateOf(false)

    var error: String by mutableStateOf("")
    override fun showError(error: String) {
        this.error = error
        println("Error: $error")
    }
}
