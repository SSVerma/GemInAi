package domain

sealed interface Result<out D, out Cause> {
    data class Success<D>(
        val data: D
    ) : Result<D, Nothing>

    data class Failure<Cause>(
        val cause: Cause
    ) : Result<Nothing, Cause>
}

typealias DefaultResult<D> = Result<D, FailureCause>