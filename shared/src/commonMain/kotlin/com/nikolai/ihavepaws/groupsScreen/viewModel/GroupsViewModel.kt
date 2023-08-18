package com.nikolai.ihavepaws.groupsScreen.viewModel

import com.nikolai.ihavepaws.localStorage.ObservableLocalStorage
import com.nikolai.ihavepaws.localStorage.realm.RealmStorage
import com.nikolai.ihavepaws.model.Group
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class GroupsViewModel constructor(
    private val storage: ObservableLocalStorage
): ViewModel() {
    private val _state = MutableStateFlow(storage.getGroupsFlow())
    val state = _state.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList<Group>()
    )


}