package com.nikolai.ihavepaws.android

import android.app.Application
import com.nikolai.ihavepaws.android.di.commonModule
import com.nikolai.ihavepaws.android.features.addGroupItemScreen.di.addGroupItemModule
import com.nikolai.ihavepaws.android.features.addGroupScreen.di.addGroupScreenModule
import com.nikolai.ihavepaws.android.features.groupScreen.di.groupScreenModule
import com.nikolai.ihavepaws.android.features.groupsScreen.di.mainScreenModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)

            modules(
                commonModule,
                mainScreenModule,
                addGroupScreenModule,
                addGroupItemModule,
                groupScreenModule
            )
        }
    }
}