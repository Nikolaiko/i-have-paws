package com.nikolai.ihavepaws.di

import com.nikolai.ihavepaws.groupsScreen.contract.GroupsScreen
import com.nikolai.ihavepaws.groupsScreen.reducer.GroupsScreenReducer
import com.nikolai.ihavepaws.localStorage.LocalStorage
import com.nikolai.ihavepaws.localStorage.realm.RealmStorage
import com.nikolai.ihavepaws.localStorage.sqldelight.SQLDelightStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(commonModule)
}

// called by iOS etc
fun initKoin() = initKoin {

}

val commonModule = module {
    single { CoroutineScope(Dispatchers.Main)  }
    single<LocalStorage> { SQLDelightStorage(get()) }
    single<GroupsScreen.Reducer> { GroupsScreenReducer(get()) }
}