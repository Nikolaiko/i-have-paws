package com.nikolai.ihavepaws.android.features.groupsScreen.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikolai.ihavepaws.android.features.groupsScreen.model.GroupsViewState
import com.nikolai.ihavepaws.groupsScreen.contract.GroupsScreen
import com.nikolai.ihavepaws.model.Group
import com.nikolai.ihavepaws.model.StateMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupsScreenViewModel @Inject constructor(
    private val reducer: GroupsScreen.Reducer
) : ViewModel() {

    private val groupsList = MutableLiveData<List<Group>>(emptyList())
    private val showAddGroup = MutableLiveData(false)

    val state = GroupsViewState(
        groups = groupsList,
        showAddGroupScreen = showAddGroup
    )

    init {
        viewModelScope.launch {
            reducer.state.collect {
                updateState(it)
            }
        }

        viewModelScope.launch {
            reducer.messages.collect {
                processMessage(it)
            }
        }
    }

    fun initGroupsList() {
        reducer.refreshGroupsList()
    }

    fun showAddGroupScreen() {
        showAddGroup.postValue(true)
    }

    fun hideAddGroupScreen() {
        showAddGroup.postValue(false)
    }

    fun deleteGroup() {

    }

    private fun updateState(newState: GroupsScreen.State) {
        groupsList.postValue(newState.groups)
    }

    private fun processMessage(newMessage: StateMessage) {

    }
}