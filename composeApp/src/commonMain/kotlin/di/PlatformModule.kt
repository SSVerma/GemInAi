package di

import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.module.Module

expect fun platformModules(): List<Module>

expect val ioDispatcher: CoroutineDispatcher