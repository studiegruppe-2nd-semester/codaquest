package com.example.codaquest.ui.components.onboarding

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/*
Questions:
 "At what level are you in coding?",
 "Which coding languages do you code in?",
 "How much time do you usually want to spend on a single project?"
 */
class OnboardingViewModel : ViewModel () {
    val questions : MutableList<String> by mutableStateOf(mutableStateListOf(
        "At what level are you in coding?",
        "Which coding languages do you code in?",
        "How much time do you usually want to spend on a single project?"
    ))

    var question1Answer :  String? by mutableStateOf(null)

    fun changeButtonColor (answer : String) {
        
    }

    var question2Answer : String by mutableStateOf("")
        private set

    fun onQuestion2AnswerChange (newValue : String) {
        question2Answer = newValue
    }




}