package domain.usecase

import di.ioDispatcher
import domain.FailureCause
import domain.Result
import domain.model.LlmGenerationArgs
import domain.model.LlmMessage
import domain.repository.ContentGenerationRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class GenerateContentUseCase(
    dispatcher: CoroutineDispatcher = ioDispatcher,
    private val repository: ContentGenerationRepository
) : ObservableResultUseCase<LlmGenerationArgs, LlmMessage, FailureCause>(dispatcher) {

    override fun execute(args: LlmGenerationArgs): Flow<Result<LlmMessage, FailureCause>> {
        return repository.generateContent(args)
    }
}
