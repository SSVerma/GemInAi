package domain

sealed interface FailureCause {
    data class ServiceError(
        val cause: String
    ) : FailureCause

    data object NoNetwork : FailureCause

    data object Unknown : FailureCause
}
