package data.remote

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.headers
import io.ktor.http.path
import io.ktor.util.StringValues
import io.ktor.util.StringValuesBuilder

class ServiceClient(
    private val environment: ServiceEnvironment,
    private val httpClient: HttpClient
) {
    suspend fun get(
        path: String,
        params: (StringValuesBuilder.() -> Unit)? = null,
        headers: (StringValuesBuilder.() -> Unit)? = null
    ) = httpClient.get(environment.baseUrl) {
        url {
            path(path)
            params?.let { providedParams ->
                parameters.appendAll(StringValues.build { providedParams() })
            }
        }
        appendAllHeaders(headers)
    }

    suspend fun post(
        path: String,
        body: Any?,
        headers: (StringValuesBuilder.() -> Unit)? = null
    ) = httpClient.post(environment.baseUrl) {
        url { path(path) }
        setBody(body)
        appendAllHeaders(headers)
    }

    private fun appendAllHeaders(extraHeaders: (StringValuesBuilder.() -> Unit)? = null) = headers {
        environment.headers?.let { serviceHeaders ->
            appendAll(StringValues.build { serviceHeaders() })
        }
        extraHeaders?.let { providedHeaders ->
            appendAll(StringValues.build { providedHeaders() })
        }
    }
}
