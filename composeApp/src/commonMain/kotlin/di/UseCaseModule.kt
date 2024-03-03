package di

import domain.usecase.GenerateContentUseCase
import org.koin.dsl.module

internal val useCaseModule = module {
    factory {
        GenerateContentUseCase(repository = get())
    }
}
