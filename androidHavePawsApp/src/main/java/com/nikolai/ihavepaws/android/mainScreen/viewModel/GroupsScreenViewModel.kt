package com.nikolai.ihavepaws.android.mainScreen.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikolai.ihavepaws.android.mainScreen.model.GroupsViewState
import com.nikolai.ihavepaws.groupsScreen.contract.GroupsScreen
import com.nikolai.ihavepaws.groupsScreen.reducer.GroupsScreenReducer
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

    val state = GroupsViewState(
        groups = groupsList
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

    fun deleteGroup() {

    }

    private fun updateState(newState: GroupsScreen.State) {
        groupsList.postValue(newState.groups)
    }

    private fun processMessage(newMessage: StateMessage) {

    }
}