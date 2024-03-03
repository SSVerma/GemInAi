package data.repository

import data.remote.ContentGenerator
import data.remote.service.OllamaApiService
import domain.FailureCause
import domain.Result
import domain.model.LlmGenerationArgs
import domain.model.LlmMessage
import domain.repository.ContentGenerationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OllamaContentGenerationRepository(
    private val apiService: OllamaApiService,
    private val contentGenerator: ContentGenerator<OllamaApiService>
) : ContentGenerationRepository {
    override fun generateContent(args: LlmGenerationArgs): Flow<Result<LlmMessage, FailureCause>> {
        return if (args.stream) {
            contentGenerator.generateStream(args, apiService)
        } else {
            flow { contentGenerator.generate(args, apiService) }
        }
    }
}
