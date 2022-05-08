package com.nikolai.ihavepaws.android.features.groupsScreen.di

import com.nikolai.ihavepaws.android.features.groupsScreen.viewModel.GroupsScreenViewModel
import com.nikolai.ihavepaws.groupsScreen.contract.GroupsScreen
import com.nikolai.ihavepaws.groupsScreen.reducer.GroupsScreenReducer
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainScreenModule = module {
    single<GroupsScreen.Reducer> { GroupsScreenReducer(get()) }
    viewModel { GroupsScreenViewModel(get(), get()) }
}
