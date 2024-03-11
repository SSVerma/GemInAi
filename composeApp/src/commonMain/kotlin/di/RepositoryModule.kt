package di

import data.remote.OllamaContentGenerator
import data.repository.OllamaContentGenerationRepository
import domain.repository.ContentGenerationRepository
import org.koin.dsl.module

internal val repositoryModule = module {
    factory {
        val registry by inject<Registry>()
        OllamaContentGenerator(model = registry.llmModel())
    }

    factory<ContentGenerationRepository> {
        OllamaContentGenerationRepository(
            apiService = get(),
            contentGenerator = get()
        )
    }
}
