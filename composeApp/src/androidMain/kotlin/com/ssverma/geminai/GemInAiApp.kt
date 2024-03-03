package com.ssverma.geminai

import android.app.Application
import android.content.Context
import di.initKoin
import org.koin.dsl.module

class GemInAiApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(
            additionalModules = listOf(
                module { single<Context> { this@GemInAiApp } }
            )
        )
    }
}
