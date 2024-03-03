package data.remote

import io.ktor.client.statement.HttpResponse

sealed interface ApiResult<out ResponseBody, out ErrorBody> {
    data class Success<ResponseBody>(
        val body: ResponseBody,
        val response: HttpResponse
    ) : ApiResult<ResponseBody, Nothing>

    sealed interface Error<ErrorBody> : ApiResult<Nothing, ErrorBody> {
        data class Server<ErrorBody>(
            val errorBody: ErrorBody?,
            val response: HttpResponse
        ) : Error<ErrorBody>

        data class Client<ErrorBody>(
            val errorBody: ErrorBody?,
            val response: HttpResponse
        ) : Error<ErrorBody>

        data object NoNetwork : Error<Nothing>

        data object Serialization : Error<Nothing>

        data object Unknown : Error<Nothing>
    }
}
