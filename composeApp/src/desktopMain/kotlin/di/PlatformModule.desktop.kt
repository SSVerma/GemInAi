package di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module

actual fun platformModules(): List<Module> {
    return emptyList()
}

actual val ioDispatcher: CoroutineDispatcher
    get() = Dispatchers.IO