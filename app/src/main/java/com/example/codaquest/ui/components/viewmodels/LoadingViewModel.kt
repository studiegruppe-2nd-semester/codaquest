package com.example.codaquest.ui.components.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoadingViewModel : ViewModel() {
    private val loadingList: List<String> = listOf(".", "..", "...")
    var loadingText: String by mutableStateOf(loadingList[0])

    init {
        // Start a coroutine to update the loading text every half second
        viewModelScope.launch {
            while (true) {
                delay(500) // Delay for half a second
                val currentIndex = loadingList.indexOf(loadingText)
                val nextIndex = (currentIndex + 1) % loadingList.size
                loadingText = loadingList[nextIndex]
            }
        }
    }
}
