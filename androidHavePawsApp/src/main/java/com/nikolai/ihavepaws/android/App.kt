package com.nikolai.ihavepaws.android

import android.app.Application
import com.nikolai.ihavepaws.di.modules.commonModule
import com.nikolai.ihavepaws.di.modules.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)

            modules(commonModule, viewModelsModule)
        }
    }
}
