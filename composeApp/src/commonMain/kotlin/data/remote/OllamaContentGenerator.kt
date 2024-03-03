package data.remote

import data.mapper.asDomainResult
import data.mapper.asLlmMessage
import data.mapper.asOllamaChatBody
import data.mapper.asOllamaPromptBody
import data.remote.request.LlmModel
import data.remote.service.OllamaApiService
import domain.FailureCause
import domain.Result
import domain.model.LlmGenerationArgs
import domain.model.LlmMessage
import kotlinx.coroutines.flow.Flow

class OllamaContentGenerator(
    private val model: LlmModel
) : ContentGenerator<OllamaApiService> {
    override suspend fun generate(
        llmGenerationArgs: LlmGenerationArgs,
        service: OllamaApiService
    ): Result<LlmMessage, FailureCause> {
        val apiResult = service.generatePromptResponse(
            llmPromptBody = llmGenerationArgs.asOllamaPromptBody(model = model)
        )
        return apiResult.asDomainResult(
            transformResponseBody = { response -> response.asLlmMessage() }
        )
    }

    override suspend fun chat(
        llmGenerationArgs: LlmGenerationArgs,
        service: OllamaApiService
    ): Result<LlmMessage, FailureCause> {
        val apiResult = service.generateChatResponse(
            llmChatBody = llmGenerationArgs.asOllamaChatBody(model = model)
        )
        return apiResult.asDomainResult(
            transformResponseBody = { response -> response.asLlmMessage() }
        )
    }

    override fun generateStream(
        llmGenerationArgs: LlmGenerationArgs,
        service: OllamaApiService
    ): Flow<Result<LlmMessage, FailureCause>> {
        TODO("Not yet implemented")
    }

    override fun chatStream(
        llmGenerationArgs: LlmGenerationArgs,
        service: OllamaApiService
    ): Flow<Result<LlmMessage, FailureCause>> {
        TODO("Not yet implemented")
    }
}