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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StorageModule {

    @Provides
    fun provideDriver(
        @ApplicationContext
        context: Context
    ): DatabaseDriverFactory = DatabaseDriverFactory(context)

    @Provides
    @Singleton
    fun provideStorage(
        factory: DatabaseDriverFactory
    ): LocalStorage = SQLDelightStorage(factory)
}