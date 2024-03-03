package di

import data.remote.OllamaContentGenerator
import data.remote.request.LlmModel
import data.remote.service.OllamaApiService
import data.repository.OllamaContentGenerationRepository
import domain.repository.ContentGenerationRepository
import org.koin.dsl.module

internal val repositoryModule = module {
    factory { (model: LlmModel) ->
        OllamaContentGenerator(model = model)
    }

    factory<ContentGenerationRepository> {
        OllamaContentGenerationRepository(
            apiService = get<OllamaApiService>(),
            contentGenerator = get<OllamaContentGenerator>()
        )
    }
}
