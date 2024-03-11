package di

import data.remote.service.DefaultOllamaApiService
import data.remote.service.OllamaApiService
import org.koin.dsl.module

internal val serviceModule = module {
    factory<OllamaApiService> {
        val registry by inject<Registry>()
        DefaultOllamaApiService(apiClient = registry.apiClient())
    }
}
