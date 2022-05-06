package com.nikolai.ihavepaws.android.features.addGroupItemScreen.di

import androidx.lifecycle.viewmodel.compose.viewModel
import com.nikolai.ihavepaws.addGroupItemScreen.contract.AddGroupItem
import com.nikolai.ihavepaws.addGroupItemScreen.reducer.AddGroupItemReducer
import com.nikolai.ihavepaws.android.features.addGroupItemScreen.viewModel.AddGroupItemViewModel
import org.koin.androidx.compose.get
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val addGroupItemModule = module {
    single<AddGroupItem.Reducer> { AddGroupItemReducer(get()) }
    viewModel { AddGroupItemViewModel(get()) }
}