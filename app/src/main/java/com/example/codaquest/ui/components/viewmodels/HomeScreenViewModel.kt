package com.example.codaquest.ui.components.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.codaquest.domain.interfaces.ErrorOperations

class HomeScreenViewModel : ViewModel(), ErrorOperations {

    override var error: String by mutableStateOf("")
    override fun showError(error: String) {
        this.error = error
        println("Error: $error")
    }
}
