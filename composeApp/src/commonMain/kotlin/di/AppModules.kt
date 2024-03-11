package di

internal fun appModules() = listOf(
    registryModule,
    networkModule,
    serviceModule,
    repositoryModule,
    useCaseModule
)