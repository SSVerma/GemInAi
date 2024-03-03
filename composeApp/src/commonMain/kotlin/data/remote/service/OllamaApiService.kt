package data.remote.service

import data.remote.ApiClient
import data.remote.ApiResult
import data.remote.request.OllamaChatBody
import data.remote.request.OllamaPromptBody
import data.remote.response.OllamaChatResponse
import data.remote.response.OllamaPromptResponse
import data.remote.response.OllamaErrorBody

interface OllamaApiService : ApiService {
    suspend fun generatePromptResponse(llmPromptBody: OllamaPromptBody): ApiResult<OllamaPromptResponse, OllamaErrorBody>

    suspend fun generateChatResponse(llmChatBody: OllamaChatBody): ApiResult<OllamaChatResponse, OllamaErrorBody>
}

class DefaultOllamaApiService(private val apiClient: ApiClient) : OllamaApiService {
    override suspend fun generatePromptResponse(
        llmPromptBody: OllamaPromptBody
    ): ApiResult<OllamaPromptResponse, OllamaErrorBody> = apiClient.postRequest(
        path = "/generate",
        body = llmPromptBody
    )

    override suspend fun generateChatResponse(
        llmChatBody: OllamaChatBody
    ): ApiResult<OllamaChatResponse, OllamaErrorBody> = apiClient.postRequest(
        path = "/chat",
        body = llmChatBody
    )
}