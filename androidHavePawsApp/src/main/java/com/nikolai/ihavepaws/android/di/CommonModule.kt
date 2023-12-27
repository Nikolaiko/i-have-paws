package com.nikolai.ihavepaws.android.di

import com.nikolai.ihavepaws.android.navigation.AppNavigator
import com.nikolai.ihavepaws.android.navigation.NavComponentNavigator
import com.nikolai.ihavepaws.services.localStorage.LocalStorage
import com.nikolai.ihavepaws.services.localStorage.stub.StubStorage
//import com.nikolai.ihavepaws.localStorage.sqldelight.DatabaseDriverFactory
//import com.nikolai.ihavepaws.localStorage.sqldelight.SQLDelightStorage
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

//val commonModule = module {
//    //single { DatabaseDriverFactory(androidContext()) }
//    single<LocalStorage> { StubStorage() }
//    single<AppNavigator> { NavComponentNavigator() }
//}
