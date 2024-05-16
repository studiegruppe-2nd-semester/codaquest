package com.example.codaquest.data.services

import com.aallam.openai.api.chat.ChatCompletion
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatResponseFormat
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.http.Timeout
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import com.example.codaquest.domain.models.GenerateProjectDetails
import com.example.codaquest.domain.models.Project
import kotlinx.serialization.json.Json
import kotlin.time.Duration.Companion.seconds

// Nathasja og Ane
class ApiService {
    private lateinit var openAI: OpenAI

    fun initiateApi(
        key: String,
    ) {
        openAI = OpenAI(
            token = key,
            timeout = Timeout(socket = 60.seconds),
            // additional configurations...
        )
    }

    suspend fun generateProjectSuggestion(
        projectInfo: GenerateProjectDetails,
        onSuccess: (Project) -> Unit,
        onError: (String) -> Unit,
    ) {
        val length = if (projectInfo.length == 0) "a number you choose of" else projectInfo.length

        val chatCompletionRequest = ChatCompletionRequest(
            model = ModelId("gpt-3.5-turbo"),
            responseFormat = ChatResponseFormat("json_object"),
            messages = listOf(
                ChatMessage(
                    role = ChatRole.System,
                    content = "You are a coding project generator. Your job is to come up with interesting projects in a chosen coding language and to give the user a project title,  project description along with steps to guide the user through the project, please make detailed steps. The project could be an app or a website, you will decide whats possible to make with the given coding language. So e.g JavaScript is typical used for website and Kotlin is typical used for apps, and if it's frontend, backend or fullstack the keywords match best with. \n" +
                        "\n" +
                        "I want the response formatted as a JSON file with title (string), description (string), steps (list of string), level (must be either Beginner, Intermediate or Advanced), language (string), length (int)",
                ),
                ChatMessage(
                    role = ChatRole.User,
                    content = "I'm ${projectInfo.level} with the coding language ${projectInfo.language} and i am looking for a project that fits these key words: ${projectInfo.keywords}. The average completion time for the project should be $length hours. ",
                ),
            ),
        )

        try {
            val completion: ChatCompletion = openAI.chatCompletion(chatCompletionRequest)
            val apiResponse = completion.choices[0].message.content

            val project = apiResponse?.let { Json.decodeFromString<Project>(it) }
            // Process the decoded project object here
            println("Project: $project")

            if (project != null) {
                onSuccess(project)
            }
        } catch (e: Exception) {
            // Handle the exception (e.g., log an error message or provide user feedback)
            e.printStackTrace()
            onError("Something went wrong.\nPlease generate new project")
        }
    }
}
