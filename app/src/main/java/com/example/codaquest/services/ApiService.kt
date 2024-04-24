package com.example.codaquest.services

import com.aallam.openai.api.chat.ChatCompletion
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatResponseFormat
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.http.Timeout
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import com.example.codaquest.ui.components.SharedViewModel
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

    suspend fun promptApi() {
        val chatCompletionRequest = ChatCompletionRequest(
            model = ModelId("gpt-3.5-turbo"),
            responseFormat = ChatResponseFormat("json_object"),
            messages = listOf(
                ChatMessage(
                    role = ChatRole.System,
                    content = "You are a coding project generator. Your job is to come up with interesting projects in a chosen coding " +
                            "language and to give the user a project title,  project description along with project requirements (which can also be " +
                            "seen as steps to complete the project)"
                ),
                ChatMessage(
                    role = ChatRole.User,
                    content = "I am looking for a project that fits these key words: \"view models, basic android app\". The project should be an" +
                            "app made by Kotlin Jetpack Compose, and the average completion time for the project should be 5 hours. I am a beginner" +
                            "in coding with Kotlin"
                )
            )
        )
        val completion: ChatCompletion = openAI.chatCompletion(chatCompletionRequest)
        // HERE YOU ONLY GET THE MESSAGE CONTENT THAT THE AI ANSWERED
        println("COMPLETION: $completion")
        println("COMPLETION: ${completion.choices[0].message.content}")

        // or, as flow
//        val completions: Flow<ChatCompletionChunk> = openAI.chatCompletions(chatCompletionRequest)
    }
}