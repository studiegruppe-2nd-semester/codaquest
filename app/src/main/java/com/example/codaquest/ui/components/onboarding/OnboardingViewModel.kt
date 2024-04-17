package com.example.codaquest.ui.components.onboarding

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.codaquest.classes.OnboardingQuestion
import com.example.codaquest.classes.OnboardingQuestionTypes

/*
Questions:
 "At what level are you in coding?",
 "Which coding languages do you code in?",
 "How much time do you usually want to spend on a single project?"
 */
@SuppressLint("MutableCollectionMutableState")
class OnboardingViewModel : ViewModel () {
    var currentQuestion: Int by mutableIntStateOf(0)
        private set

    var nextButton: String by mutableStateOf("Next")
        private set

    fun nextQuestion(
        navController: NavController
    ) {
        if ((currentQuestion < questions.size - 1) && questions[currentQuestion].answer.value.isNotEmpty()) {
            currentQuestion++
            if (currentQuestion == questions.size - 1) {
                nextButton = "Finish"
            }
        }
        else if ((currentQuestion == questions.size - 1) && questions[currentQuestion].answer.value.isNotEmpty()) {
            navController.navigate("home")
        }
    }

    val questions : List<OnboardingQuestion> by mutableStateOf(mutableStateListOf(
        OnboardingQuestion(
            question = "At what level are you in coding?",
            options = listOf("Beginner", "Intermediate", "Advanced"),
            type = OnboardingQuestionTypes.RadioButton
        ),
        OnboardingQuestion(
            question = "Which coding languages do you code in?",
            type = OnboardingQuestionTypes.TextField
        ),
        OnboardingQuestion(
            question = "How much time do you usually want to spend on a single project? Answer in whole hours",
            type = OnboardingQuestionTypes.IntField
        ),
    ))

//    var question1Answer :  String? by mutableStateOf(null)

    fun addButtonColor (answer : String) {
        if (answer == questions[currentQuestion].answer.value) {
            return
        }
        else {
            return
        }
    }

//    var question2Answer : String by mutableStateOf("")
//        private set
//
//    fun onQuestion2AnswerChange (newValue : String) {
//        question2Answer = newValue
//    }




}