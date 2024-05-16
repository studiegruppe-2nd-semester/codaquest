package com.example.codaquest.ui.components.viewmodels

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.codaquest.domain.interfaces.ErrorOperations
import com.example.codaquest.domain.models.GenerateProjectDetails
import com.example.codaquest.domain.models.QuestionSettings
import com.example.codaquest.domain.models.QuestionTypes

class HomeScreenViewModel :

   /* (
   private val sharedViewModel: SharedViewModel,
    private val homeScreenViewModel: HomeScreenViewModel
            ) */

    ViewModel(), ErrorOperations {

    var generateProjectDetails: GenerateProjectDetails by mutableStateOf(GenerateProjectDetails())

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

    fun nextQuestion() {
        if ((currentQuestion < questions.size - 1) && questions[currentQuestion].answer.value.isNotEmpty()) {
            currentQuestion++
            if (currentQuestion == questions.size - 1) {
                nextButton = "Generate project"
            }
        }

        // Indsæt en else if hvor at når alt lort er færdigt skal der generes et projekt.
        else if ((currentQuestion == questions.size - 1) && questions[currentQuestion].answer.value.isNotEmpty()) {
            // Her mangler jeg en function til at genere et projekt når jeg er done.
        }
    }

    val questions: List<QuestionSettings> by mutableStateOf(
        mutableStateListOf(
            QuestionSettings(
                question = "What do you want the project to be about? (keywords)",
                type = QuestionTypes.TextField,
            ),
            QuestionSettings(
                question = "What level you want the project to be in? (level)",
                options = listOf("Beginner", "Intermediate", "Advanced"),
                type = QuestionTypes.RadioButton,
            ),
            QuestionSettings(
                question = "What coding language do you want the project to be in? (language)",
                type = QuestionTypes.TextField,
            ),
            QuestionSettings(
                question = "How much time do you want to use on the project? (hours)",
                type = QuestionTypes.IntField,
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

    override var error: String by mutableStateOf("")
    override fun showError(error: String) {
        this.error = error
        println("Error: $error")
    }
}
