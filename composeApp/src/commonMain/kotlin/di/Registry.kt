package di

import data.remote.ApiClient
import data.remote.request.LlmModel
import org.koin.dsl.module

val registryModule = module {
    single { Registry() }
}

class Registry {
    private lateinit var registryValue: RegistryValue

    fun <T : RegistryValue> register(registryValue: T) {
        this.registryValue = registryValue
    }

    fun apiClient(): ApiClient {
        return registryValue.apiClient
    }

    fun llmModel(): LlmModel {
        return registryValue.model
    }
}
