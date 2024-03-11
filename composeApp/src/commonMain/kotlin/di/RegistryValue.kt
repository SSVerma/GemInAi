package di

import data.remote.ApiClient
import data.remote.ServiceClient
import data.remote.ServiceEnvironment
import data.remote.request.LlmModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

interface RegistryValue : KoinComponent {
    val model: LlmModel
    val apiClient: ApiClient
}

class OllamaRegistryValue(
    environment: ServiceEnvironment,
    override val model: LlmModel
) : RegistryValue {

    override val apiClient: ApiClient by lazy {
        ApiClient(
            serviceClient = ServiceClient(
                environment = environment,
                httpClient = get()
            )
        )
    }
}
