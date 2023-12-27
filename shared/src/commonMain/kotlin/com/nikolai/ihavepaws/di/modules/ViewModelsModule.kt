package com.nikolai.ihavepaws.di.modules

import com.nikolai.ihavepaws.appScreens.viewModels.AppViewModel
import com.nikolai.ihavepaws.di.extensions.viewModelDefinition
import com.nikolai.ihavepaws.groupsScreen.viewModel.GroupsViewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModelDefinition { GroupsViewModel(get()) }
    viewModelDefinition { AppViewModel() }
}