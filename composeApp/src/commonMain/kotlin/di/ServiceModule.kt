package di

import data.remote.ApiClient
import data.remote.service.DefaultOllamaApiService
import data.remote.service.OllamaApiService
import org.koin.dsl.module

internal val serviceModule = module {
    factory<OllamaApiService> { (apiClient: ApiClient) ->
        DefaultOllamaApiService(apiClient = apiClient)
    }
}
