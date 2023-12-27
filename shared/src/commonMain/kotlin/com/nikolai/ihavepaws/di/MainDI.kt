package com.nikolai.ihavepaws.di

import com.nikolai.ihavepaws.di.modules.commonModule
import com.nikolai.ihavepaws.di.modules.viewModelsModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(commonModule, viewModelsModule)
    }
}