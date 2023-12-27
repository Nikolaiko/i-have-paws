package com.nikolai.ihavepaws.groupsScreen.viewModel

import com.nikolai.ihavepaws.groupsScreen.contract.GroupsScreen
import com.nikolai.ihavepaws.services.localStorage.ObservableLocalStorage
import com.nikolai.ihavepaws.model.Group
import com.nikolai.ihavepaws.model.consts.sharingCoroutineDelay
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class GroupsViewModel constructor(
    private val storage: ObservableLocalStorage
): ViewModel() {
    private val _state = MutableStateFlow(GroupsScreen.State())
    val state = combine(
        _state,
        storage.getGroupsFlow()
    ) { state, groups ->
        state.copy(
            groups = groups
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(sharingCoroutineDelay),
        initialValue = GroupsScreen.State()
    )

    fun onEvent(event: GroupsScreen.Event) {
        when(event) {
            GroupsScreen.Event.AddNewGroup -> {
                _state.update {
                    it.copy(addGroupVisible = true)
                }
            }
        }
    }
}