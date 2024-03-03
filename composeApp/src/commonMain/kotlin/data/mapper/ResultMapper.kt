package data.mapper

import data.remote.ApiResult
import domain.FailureCause
import domain.Result

fun <ResponseBody, ErrorBody, DomainData, DomainFailureCause> ApiResult<ResponseBody, ErrorBody>.asDomainResult(
    transformResponseBody: (ResponseBody) -> DomainData,
    transformErrorWithBody: (ApiResult.Error<ErrorBody>) -> DomainFailureCause,
    transformErrorWithoutBody: (ApiResult.Error<Nothing>) -> DomainFailureCause
): Result<DomainData, DomainFailureCause> {
    when (this) {
        is ApiResult.Success -> {
            return Result.Success(
                data = transformResponseBody(this.body)
            )
        }

        is ApiResult.Error.Server -> {
            return Result.Failure(cause = transformErrorWithBody(this))
        }

        is ApiResult.Error.Client -> {
            return Result.Failure(cause = transformErrorWithBody(this))
        }

        is ApiResult.Error.Serialization -> {
            return Result.Failure(cause = transformErrorWithoutBody(this))
        }

        is ApiResult.Error.Unknown -> {
            return Result.Failure(cause = transformErrorWithoutBody(this))
        }

        is ApiResult.Error.NoNetwork -> {
            return Result.Failure(cause = transformErrorWithoutBody(this))
        }
    }
}

fun <ResponseBody, ErrorBody, DomainData> ApiResult<ResponseBody, ErrorBody>.asDomainResult(
    transformResponseBody: (ResponseBody) -> DomainData,
    transformErrorBody: (ErrorBody?) -> String? = { null }
): Result<DomainData, FailureCause> {
    when (this) {
        is ApiResult.Success -> {
            return Result.Success(
                data = transformResponseBody(this.body)
            )
        }

        is ApiResult.Error.Server -> {
            val causeMessage =
                transformErrorBody(this.errorBody) ?: this.response.status.description
            return Result.Failure(cause = FailureCause.ServiceError(cause = causeMessage))
        }

        is ApiResult.Error.Client -> {
            return Result.Failure(cause = FailureCause.Unknown)
        }

        is ApiResult.Error.Serialization -> {
            return Result.Failure(cause = FailureCause.Unknown)
        }

        is ApiResult.Error.Unknown -> {
            return Result.Failure(cause = FailureCause.Unknown)
        }

        is ApiResult.Error.NoNetwork -> {
            return Result.Failure(cause = FailureCause.NoNetwork)
        }
    }
}
