package com.example.codaquest.ui.components.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class HomeScreenViewModel : ViewModel () {
    // ------------------------------------- KEYWORDS
    var keyWords: String by mutableStateOf("")
        private set
    fun updateKeyWords(newKeyWords: String) {
        keyWords = newKeyWords
    }
    // ------------------------------------- LANGUAGE
    var language: String by mutableStateOf("")
        private set
    fun updateLanguage(newLanguage: String) {
        language = newLanguage
    }
    // ------------------------------------- LENGTH
    var length: Int by mutableIntStateOf(0)
        private set
    fun updateLength(newLength: String) {
        length = try {
            newLength.toInt()
        } catch (e: NumberFormatException) {
            // Handle the case where the input cannot be converted to an integer
            // For example, you can set a default value or display an error message
            0 // Set a default value of 0
        }
    }
    // ------------------------------------- LEVEL
    var level: String by mutableStateOf("")
        private set
    fun updateLevel(newLevel: String) {
        level = newLevel
    }
}