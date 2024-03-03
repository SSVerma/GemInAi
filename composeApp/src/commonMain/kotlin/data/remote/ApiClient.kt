package data.remote

import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.StringValuesBuilder
import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.SerializationException

class ApiClient(val serviceClient: ServiceClient) {
    suspend inline fun <reified ResponseBody, reified ErrorBody> getRequest(
        path: String,
        noinline params: (StringValuesBuilder.() -> Unit)? = null,
        noinline headers: (StringValuesBuilder.() -> Unit)? = null,
    ): ApiResult<ResponseBody, ErrorBody> {
        val response = serviceClient.get(path, params, headers)
        return response.asApiResult()
    }

    suspend inline fun <RequestBody, reified ResponseBody, reified ErrorBody> postRequest(
        path: String,
        body: RequestBody,
        noinline headers: (StringValuesBuilder.() -> Unit)? = null,
    ): ApiResult<ResponseBody, ErrorBody> {
        val response = serviceClient.post(path, body, headers)
        return response.asApiResult()
    }

    suspend inline fun <reified ResponseBody, reified ErrorBody> HttpResponse.asApiResult(): ApiResult<ResponseBody, ErrorBody> {
        return try {
            val body = this.body<ResponseBody>()
            ApiResult.Success(
                body = body,
                response = this
            )
        } catch (e: ClientRequestException) {
            ApiResult.Error.Client(
                errorBody = e.errorBody(),
                response = this
            )
        } catch (e: ServerResponseException) {
            ApiResult.Error.Server(
                errorBody = e.errorBody(),
                response = this
            )
        } catch (e: SerializationException) {
            ApiResult.Error.Serialization
        } catch (e: IOException) {
            ApiResult.Error.NoNetwork
        } catch (e: Exception) {
            ApiResult.Error.Unknown
        }
    }

    suspend inline fun <reified ErrorBody> ResponseException.errorBody(): ErrorBody? {
        return try {
            response.body()
        } catch (e: SerializationException) {
            null
        }
    }
}