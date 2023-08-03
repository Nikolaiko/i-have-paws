package com.nikolai.ihavepaws.android.features.groupsScreen.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikolai.ihavepaws.android.features.groupsScreen.model.GroupsViewState
import com.nikolai.ihavepaws.android.model.ViewModelMessage
import com.nikolai.ihavepaws.android.navigation.AppNavigator
import com.nikolai.ihavepaws.android.navigation.AppScreens
import com.nikolai.ihavepaws.groupsScreen.contract.GroupsScreen
import com.nikolai.ihavepaws.model.Group
import com.nikolai.ihavepaws.model.StateMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupsScreenViewModel @Inject constructor(
    private val reducer: GroupsScreen.Reducer,
    private val navigation: AppNavigator
) : ViewModel() {

    private val groupsList = MutableLiveData<List<Group>>(emptyList())
    private val showAddGroup = MutableLiveData(false)
    private val messages = MutableSharedFlow<ViewModelMessage>()

    val state = GroupsViewState(
        groups = groupsList,
        showAddGroupScreen = showAddGroup,
        messages = messages
    )

//    init {
//        viewModelScope.launch {
//            reducer.state.collect {
//                updateState(it)
//            }
//        }
//
//        viewModelScope.launch {
//            reducer.messages.collect {
//                messages.emit(ViewModelMessage.Error(it.text))
//            }
//        }
//    }

    fun initGroupsList() {
        reducer.refreshGroupsList()
    }

    fun showAddGroupScreen() {
        showAddGroup.postValue(true)
    }

    fun hideAddGroupScreen() {
        showAddGroup.postValue(false)
    }

    fun deleteGroup(item: Group) {
        reducer.removeGroup(item.name)
        reducer.refreshGroupsList()
    }

    fun openGroup(item: Group) {
        val groupRoute = AppScreens.GroupScreen
        groupRoute.buildRoute(item.name)

        navigation.navigateTo(groupRoute)
    }

//    private fun updateState(newState: GroupsScreen.State) {
//        groupsList.postValue(newState.groups)
//    }
}
