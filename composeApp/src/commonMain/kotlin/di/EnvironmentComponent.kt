package di

import data.remote.ContentGenerator
import data.remote.OllamaContentGenerator
import data.remote.ApiClient
import data.remote.ServiceClient
import data.remote.ServiceEnvironment
import data.remote.request.LlmModel
import data.remote.service.ApiService
import data.remote.service.OllamaApiService
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

interface EnvironmentComponent<T : ApiService> : KoinComponent {
    val apiService: T
    val contentGenerator: ContentGenerator<T>
}

class OllamaEnvironmentComponent(
    model: LlmModel,
    environment: ServiceEnvironment
) : EnvironmentComponent<OllamaApiService> {

    override val apiService: OllamaApiService by inject {
        val apiClient = ApiClient(
            serviceClient = ServiceClient(
                environment = environment,
                httpClient = get()
            )
        )
        parametersOf(apiClient)
    }

    override val contentGenerator: OllamaContentGenerator by inject {
        parametersOf(model)
    }
}
