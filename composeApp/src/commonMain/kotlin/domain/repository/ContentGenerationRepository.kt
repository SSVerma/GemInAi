package domain.repository

import domain.FailureCause
import domain.Result
import domain.model.LlmGenerationArgs
import domain.model.LlmMessage
import kotlinx.coroutines.flow.Flow

interface ContentGenerationRepository {
    fun generateContent(args: LlmGenerationArgs): Flow<Result<LlmMessage, FailureCause>>
}
