package com.example.codaquest.services

import com.aallam.openai.api.chat.ChatCompletion
import com.aallam.openai.api.chat.ChatCompletionChunk
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.http.Timeout
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import com.example.codaquest.ui.components.SharedViewModel
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

    suspend fun promptApi() {
        val chatCompletionRequest = ChatCompletionRequest(
            model = ModelId("gpt-3.5-turbo"),
            messages = listOf(
                ChatMessage(
                    role = ChatRole.System,
                    content = "You are a helpful assistant!"
                ),
                ChatMessage(
                    role = ChatRole.User,
                    content = "Hello!"
                )
            )
        )
        val completion: ChatCompletion = openAI.chatCompletion(chatCompletionRequest)
        println("COMPLETION: $completion")
        // or, as flow
        val completions: Flow<ChatCompletionChunk> = openAI.chatCompletions(chatCompletionRequest)
    }
}