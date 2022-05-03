package com.nikolai.ihavepaws.android.di

import android.content.Context
import com.nikolai.ihavepaws.localStorage.LocalStorage
import com.nikolai.ihavepaws.localStorage.sqldelight.DatabaseDriverFactory
import com.nikolai.ihavepaws.localStorage.sqldelight.SQLDelightStorage
import com.squareup.sqldelight.db.SqlDriver
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import java.sql.DriverManager
import javax.inject.Singleton

val storageModule = module {
    single { DatabaseDriverFactory(androidContext()) }
    single<LocalStorage> { SQLDelightStorage(get()) }
}