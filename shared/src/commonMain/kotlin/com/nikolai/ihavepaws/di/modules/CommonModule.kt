package com.nikolai.ihavepaws.di.modules

import com.nikolai.ihavepaws.localStorage.LocalStorage
import com.nikolai.ihavepaws.localStorage.ObservableLocalStorage
import com.nikolai.ihavepaws.localStorage.realm.RealmStorage
import com.nikolai.ihavepaws.localStorage.realm.dbObjects.RandomGroup
import com.nikolai.ihavepaws.localStorage.realm.realmDbSchema
import com.nikolai.ihavepaws.localStorage.realm.realmSchemaVersion
import com.nikolai.ihavepaws.localStorage.sqldelight.DatabaseDriverFactory
import com.nikolai.ihavepaws.localStorage.sqldelight.SQLDelightStorage
import io.realm.kotlin.RealmConfiguration
import org.koin.core.module.dsl.singleOf
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