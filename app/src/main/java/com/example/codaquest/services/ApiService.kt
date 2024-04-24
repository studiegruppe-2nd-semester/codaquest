package com.example.codaquest.services

import com.aallam.openai.api.chat.ChatCompletion
import com.aallam.openai.api.chat.ChatCompletionChunk
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.http.Timeout
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import com.example.codaquest.classes.Project
import com.example.codaquest.ui.components.SharedViewModel
import com.example.codaquest.ui.components.home.HomeScreenViewModel
import kotlinx.coroutines.flow.Flow
import kotlin.time.Duration.Companion.seconds

class ApiService {
    private lateinit var openAI: OpenAI

    fun initiateApi(
        sharedViewModel: SharedViewModel
    ) {
        openAI = sharedViewModel.key?.let {
            OpenAI(
                token = it,
                timeout = Timeout(socket = 60.seconds),
                // additional configurations...
            )
        }!!
    }

    suspend fun promptApi(homeScreenViewModel: HomeScreenViewModel) {
        val chatCompletionRequest = ChatCompletionRequest(
            model = ModelId("gpt-3.5-turbo"),
            messages = listOf(
                ChatMessage(
                    role = ChatRole.System,
                    content = "You are a coding project generator. Your job is to come up with interesting projects in a chosen coding language and to give the user a project title,  project description along with steps to guide the user through the project, please make detailed steps. The project could be an app or a website, you will decide whats possible to make with the given coding language. So e.g JavaScript is typical used for website and Kotlin is typical used for apps, and if it's frontend, backend or fullstack the keywords match best with. \n" +
                            "\n" +
                            "I want the response formatted as a JSON file with title (string), description (string), steps (list of string), level (int), language (string), length (int)"
                ),
                ChatMessage(
                    role = ChatRole.User,
                    content = "I'm ${homeScreenViewModel.level} with the coding language ${homeScreenViewModel.language} and i am looking for a project that fits these key words: ${homeScreenViewModel.keyWords}. The average completion time for the project should be ${homeScreenViewModel.length} hours. "
                )
            )
        )
        val completion: ChatCompletion = openAI.chatCompletion(chatCompletionRequest)
        println("COMPLETION: $completion")
        // or, as flow
        val completions: Flow<ChatCompletionChunk> = openAI.chatCompletions(chatCompletionRequest)
    }
}