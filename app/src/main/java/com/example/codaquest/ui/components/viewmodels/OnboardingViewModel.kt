package com.example.codaquest.ui.components.viewmodels

import android.annotation.SuppressLint
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.codaquest.domain.models.OnboardingData
import com.example.codaquest.domain.models.OnboardingQuestion
import com.example.codaquest.domain.models.OnboardingQuestionTypes
import com.example.codaquest.domain.models.User
import com.example.codaquest.domain.models.stringToLevelType
import com.example.codaquest.data.repositories.UserRepository

@SuppressLint("MutableCollectionMutableState")
class OnboardingViewModel : ViewModel() {
    private val userRepository: UserRepository = UserRepository()
    var currentQuestion: Int by mutableIntStateOf(0)
        private set

    var nextButton: String by mutableStateOf("Next")
        private set

    fun previousQuestion() {
        if (currentQuestion > 0) {
            currentQuestion--
            if (nextButton != "Next") nextButton = "Next"
        }
    }

    fun nextQuestion(
        user: User,
        onSuccess: (User) -> Unit,
    ) {
        if ((currentQuestion < questions.size - 1) && questions[currentQuestion].answer.value.isNotEmpty()) {
            currentQuestion++
            if (currentQuestion == questions.size - 1) {
                nextButton = "Finish"
            }
        }
        // IF THE LAST QUESTION IS ANSWERED, ADD TO FIRESTORE
        else if ((currentQuestion == questions.size - 1) && questions[currentQuestion].answer.value.isNotEmpty()) {
            userRepository.addOnboardingDataToUserData(
                OnboardingData(
                    level = questions[0].answer.value.let { stringToLevelType(it) },
                    languages = questions[1].answer.value,
                    projectLength = questions[2].answer.value.toInt(),
                ),
                user,
                onSuccess = { onSuccess(it) },
            )
        }
    }

    val questions: List<OnboardingQuestion> by mutableStateOf(
        mutableStateListOf(
            OnboardingQuestion(
                question = "At what level are you in coding?",
                options = listOf("Beginner", "Intermediate", "Advanced"),
                type = OnboardingQuestionTypes.RadioButton,
            ),
            OnboardingQuestion(
                question = "Which coding languages do you code in?",
                type = OnboardingQuestionTypes.TextField,
            ),
            OnboardingQuestion(
                question = "How much time do you usually want to spend on a single project? Answer in whole hours",
                type = OnboardingQuestionTypes.IntField,
            ),
        ),
    )

    @Composable
    fun addButtonColor(answer: String): Color {
        return if (answer == questions[currentQuestion].answer.value) {
            MaterialTheme.colorScheme.tertiary
        } else {
            MaterialTheme.colorScheme.secondary
        }
    }
}
