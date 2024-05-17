package com.example.codaquest.ui.components.viewmodels

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codaquest.data.services.ApiService
import com.example.codaquest.domain.interfaces.ErrorOperations
import com.example.codaquest.domain.models.GenerateProjectDetails
import com.example.codaquest.domain.models.OnboardingData
import com.example.codaquest.domain.models.Project
import com.example.codaquest.domain.models.QuestionInfo
import com.example.codaquest.domain.models.QuestionTypes
import com.example.codaquest.domain.models.stringToLevelType
import kotlinx.coroutines.launch

@SuppressLint("MutableCollectionMutableState")
class GenerateProjectViewModel : ViewModel(), ErrorOperations {
    override var error: String by mutableStateOf("")
    override fun showError(error: String) {
        this.error = error
    }

    var dropdownExpanded: Boolean by mutableStateOf(false)

    val questions: List<QuestionInfo> by mutableStateOf(
        mutableStateListOf(
            QuestionInfo(
                question = "What do you want the project to be about? Write your keywords here",
                type = QuestionTypes.TextField,
            ),
            QuestionInfo(
                question = "What level you want the project to be in?",
                options = listOf("Beginner", "Intermediate", "Advanced"),
                type = QuestionTypes.RadioButton,
            ),
            QuestionInfo(
                question = "What coding language do you want the project to be in?",
                type = QuestionTypes.TextField,
            ),
            QuestionInfo(
                question = "How much time do you want to use on the project in whole hours?",
                type = QuestionTypes.IntField,
            ),
        ),
    )

    fun addOnboardingAnswersAsDefaultAnswers(
        onboardingData: OnboardingData
    ) {
        questions[1].answer.value = onboardingData.level.toString()
        questions[2].dropdownAnswer.value = "Choose known coding language"
        questions[3].answer.value = onboardingData.projectLength.toString()
    }

    var currentQuestion: Int by mutableIntStateOf(0)
        private set

    fun previousQuestion() {
        if (currentQuestion > 0) {
            currentQuestion--
        }
    }

    fun nextQuestion() {
        if (error.isNotEmpty()) {
            showError("")
        }

        if ((currentQuestion < questions.size - 1) && questions[currentQuestion].answer.value.isNotEmpty()) {
            currentQuestion++
        }
        else if ((currentQuestion < questions.size - 1) && !questions[currentQuestion].dropdownAnswer.value.contains("choose", ignoreCase = true)) {
            questions[currentQuestion].answer.value = questions[currentQuestion].dropdownAnswer.value
            currentQuestion++
        }
        else {
            showError("Please enter a value")
        }
    }

    fun getProjectSuggestion(
        key: String,
        onSuccess: (Project) -> Unit,
    ) {
        val apiService = ApiService()
        apiService.initiateApi(key)

        val generateProjectDetails = GenerateProjectDetails(
            keywords = questions[0].answer.value,
            level = stringToLevelType(questions[1].answer.value),
            language = questions[2].answer.value,
            length = questions[3].answer.value.toInt(),
        )

        viewModelScope.launch {
            apiService.generateProjectSuggestion(
                generateProjectDetails,
                onSuccess = { onSuccess(it) },
                onError = { error -> showError(error) },
            )
        }
    }

    var showGeneratedProject: Boolean by mutableStateOf(false)
}
