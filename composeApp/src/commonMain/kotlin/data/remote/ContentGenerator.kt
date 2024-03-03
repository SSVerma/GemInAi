package data.remote

import data.remote.service.ApiService
import domain.FailureCause
import domain.Result
import domain.model.LlmGenerationArgs
import domain.model.LlmMessage
import kotlinx.coroutines.flow.Flow

interface ContentGenerator<T : ApiService> {
    suspend fun generate(
        llmGenerationArgs: LlmGenerationArgs,
        service: T
    ): Result<LlmMessage, FailureCause>

    suspend fun chat(
        llmGenerationArgs: LlmGenerationArgs,
        service: T
    ): Result<LlmMessage, FailureCause>

    fun generateStream(
        llmGenerationArgs: LlmGenerationArgs,
        service: T
    ): Flow<Result<LlmMessage, FailureCause>>

    fun chatStream(
        llmGenerationArgs: LlmGenerationArgs,
        service: T
    ): Flow<Result<LlmMessage, FailureCause>>
}
