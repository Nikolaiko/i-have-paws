package com.nikolai.ihavepaws.di.modules

import com.nikolai.ihavepaws.services.localStorage.LocalStorage
import com.nikolai.ihavepaws.services.localStorage.ObservableLocalStorage
import com.nikolai.ihavepaws.services.localStorage.realm.RealmStorage
import com.nikolai.ihavepaws.services.localStorage.realm.realmDbSchema
import com.nikolai.ihavepaws.services.localStorage.realm.realmSchemaVersion
import io.realm.kotlin.RealmConfiguration
import org.koin.dsl.module

val commonModule = module {
    val config = RealmConfiguration
        .Builder(schema = realmDbSchema)
        .schemaVersion(schemaVersion = realmSchemaVersion)
        .build()
    val realmStorage = RealmStorage(config)

    single<LocalStorage> { realmStorage }
    single<ObservableLocalStorage> { realmStorage }
}