package com.example.codaquest.ui.components.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.codaquest.domain.models.GenerateProjectDetails
import com.example.codaquest.interfaces.ErrorOperations

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
