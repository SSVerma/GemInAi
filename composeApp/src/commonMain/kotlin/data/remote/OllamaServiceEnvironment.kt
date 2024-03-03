package data.remote

import io.ktor.util.StringValuesBuilder

data class OllamaServiceEnvironment(
    override val baseUrl: String = "http://localhost:11434/api",
    override val headers: (StringValuesBuilder.() -> Unit)? = null
) : ServiceEnvironment
