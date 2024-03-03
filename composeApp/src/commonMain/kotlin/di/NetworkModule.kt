package di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

internal val networkModule = module {
    single { provideJson() }
    single { provideHttpClient(get()) }
}

fun provideHttpClient(json: Json): HttpClient {
    return HttpClient {
        install(ContentNegotiation) {
            json(
                json = json,
                contentType = ContentType.Application.Json
            )
        }
        defaultRequest {
            contentType(ContentType.Application.Json)
        }
    }
}

fun provideJson(): Json {
    return Json { ignoreUnknownKeys = true }
}
