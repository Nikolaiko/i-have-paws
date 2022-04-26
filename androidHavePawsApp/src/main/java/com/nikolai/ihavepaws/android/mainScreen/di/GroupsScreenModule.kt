package com.nikolai.ihavepaws.android.mainScreen.di

import com.nikolai.ihavepaws.android.mainScreen.viewModel.GroupsScreenViewModel
import com.nikolai.ihavepaws.groupsScreen.contract.GroupsScreen
import com.nikolai.ihavepaws.groupsScreen.reducer.GroupsScreenReducer
import com.nikolai.ihavepaws.localStorage.LocalStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class GroupsScreenModule {

    @Provides
    fun providesGroupsReducer(
        storage: LocalStorage
    ): GroupsScreen.Reducer = GroupsScreenReducer(storage)

    @Provides
    fun provideGroupsViewModel(
        reducer: GroupsScreen.Reducer
    ): GroupsScreenViewModel = GroupsScreenViewModel(reducer)
}