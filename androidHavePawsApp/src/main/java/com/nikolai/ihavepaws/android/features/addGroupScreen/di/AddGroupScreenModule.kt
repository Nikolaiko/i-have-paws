package com.nikolai.ihavepaws.android.features.addGroupScreen.di

import com.nikolai.ihavepaws.addGroupScreen.contract.AddGroup
import com.nikolai.ihavepaws.addGroupScreen.reducer.AddGroupReducer
import com.nikolai.ihavepaws.android.features.addGroupScreen.viewModel.AddGroupViewModel
import com.nikolai.ihavepaws.android.features.groupsScreen.viewModel.GroupsScreenViewModel
import com.nikolai.ihavepaws.groupsScreen.contract.GroupsScreen
import com.nikolai.ihavepaws.groupsScreen.reducer.GroupsScreenReducer
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val addGroupScreenModule = module {
    single<AddGroup.Reducer> { AddGroupReducer(get()) }
    viewModel { AddGroupViewModel(get()) }
}
