package domain.usecase

import domain.FailureCause
import domain.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

abstract class UseCase<in Args, D>(
    private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(args: Args): D = withContext(dispatcher) {
        return@withContext execute(args)
    }

    protected abstract suspend fun execute(args: Args): D
}

abstract class ResultUseCase<in Args, Data, FailureCause>(dispatcher: CoroutineDispatcher) :
    UseCase<Args, Result<Data, FailureCause>>(dispatcher)

abstract class ObservableUseCase<in Args, D>(
    private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(args: Args): Flow<D> {
        return execute(args).flowOn(dispatcher)
    }

    protected abstract fun execute(args: Args): Flow<D>
}

abstract class ObservableResultUseCase<in Args, Data, FailureCause>(dispatcher: CoroutineDispatcher) :
    ObservableUseCase<Args, Result<Data, FailureCause>>(dispatcher)
