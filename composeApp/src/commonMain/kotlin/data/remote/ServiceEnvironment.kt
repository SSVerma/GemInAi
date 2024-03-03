package data.remote

import io.ktor.util.StringValuesBuilder

interface ServiceEnvironment {
    val baseUrl: String
    val headers: (StringValuesBuilder.() -> Unit)?
}
