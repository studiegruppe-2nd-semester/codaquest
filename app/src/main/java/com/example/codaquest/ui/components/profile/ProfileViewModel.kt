package com.example.codaquest.ui.components.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.codaquest.models.OnboardingData
import com.example.codaquest.models.User
import com.example.codaquest.models.stringToLevelType
import com.example.codaquest.repositories.UserRepository
import com.example.codaquest.services.AccountService

class ProfileViewModel : ViewModel() {
    private val accountService = AccountService()
    private val userRepository: UserRepository = UserRepository()

    val onboardingAnswerTitles: Map<String, String> = mapOf(
        "level" to "Level in coding:",
        "languages" to "Known coding languages:",
        "project-length" to "Preferred project length (in hours):",
    )

    var editingOnboardingAnswers: Boolean by mutableStateOf(false)

    fun toggleEditingOnboardingAnswers() {
        editingOnboardingAnswers = !editingOnboardingAnswers
    }

    var level: String by mutableStateOf("")
    var languages: String by mutableStateOf("")
    var projectLength: String by mutableStateOf("")

    fun editOnboardingAnswers(titleKey: String, newValue: String) {
        when (titleKey) {
            "level" -> level = newValue
            "languages" -> languages = newValue
            "project-length" -> projectLength = newValue
        }
    }

    fun getOnboardingAnswers(titleKey: String): String {
        return when (titleKey) {
            "level" -> level
            "languages" -> languages
            "project-length" -> projectLength
            else -> ""
        }
    }

    fun logout(
        onSuccess: () -> Unit,
    ) {
        accountService.logout(onSuccess = { onSuccess() })
    }

    fun saveOnboardingAnswers(
        user: User,
        onSuccess: (User) -> Unit,
    ) {
        userRepository.addOnboardingDataToUserData(
            OnboardingData(
                level = stringToLevelType(level),
                languages = languages,
                projectLength = projectLength.toIntOrNull(),
            ),
            user,
            onSuccess = { onSuccess(it) },
        )
    }
}
