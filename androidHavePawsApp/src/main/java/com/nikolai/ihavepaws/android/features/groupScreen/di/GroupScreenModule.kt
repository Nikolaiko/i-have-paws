package com.nikolai.ihavepaws.android.features.groupScreen.di

import com.nikolai.ihavepaws.android.features.groupScreen.viewModel.GroupScreenViewModel
import com.nikolai.ihavepaws.groupScreen.contract.GroupScreen
import com.nikolai.ihavepaws.groupScreen.reducer.GroupScreenReducer
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val groupScreenModule = module {
    single<GroupScreen.Reducer> { GroupScreenReducer(get()) }
    viewModel { GroupScreenViewModel(get()) }
}