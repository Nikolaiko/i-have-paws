package com.nikolai.ihavepaws.android.features.groupScreen.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikolai.ihavepaws.android.features.groupScreen.model.GroupScreenState
import com.nikolai.ihavepaws.android.model.ViewModelMessage
import com.nikolai.ihavepaws.android.navigation.AppNavigator
import com.nikolai.ihavepaws.android.navigation.AppScreens
import com.nikolai.ihavepaws.groupScreen.contract.GroupScreen
import com.nikolai.ihavepaws.model.GroupItem
import com.nikolai.ihavepaws.model.StateMessage
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class GroupScreenViewModel(
    private val reducer: GroupScreen.Reducer,
    private val navigation: AppNavigator
) : ViewModel() {

    private val groupsItemsList = MutableLiveData<List<GroupItem>>(emptyList())
    private val randomItem = MutableLiveData<GroupItem?>(null)
    private val showAddItem = MutableLiveData(false)
    private val selectedGroupId = MutableLiveData("")
    private val randomButtonEnabled = MutableLiveData(false)
    private val messages = MutableSharedFlow<ViewModelMessage>()

    val state = GroupScreenState(
        randomButtonEnabled = randomButtonEnabled,
        addItemMenuShow = showAddItem,
        messages = messages,
        groupItems = groupsItemsList,
        selectedGroupId = selectedGroupId,
        randomItem = randomItem
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
//                when(it) {
//                    is StateMessage.ErrorMessage -> messages.emit(ViewModelMessage.Error(it.text))
//
//                    is StateMessage.InfoMessage,
//                    is StateMessage.SuccessMessage -> messages.emit(ViewModelMessage.Info(it.text))
//
//                    is StateMessage.SelectedItemMessage -> {
//                        randomItem.postValue(it.selectedItem)
//                    }
//                }
//            }
//        }
//    }

    fun initWithGroup(name: String) {
        reducer.getGroupByName(name)
    }

    fun selectRandomElement() {
        reducer.selectRandomElement()
    }

    fun toggleGroupItemStatus(item: GroupItem) {
        reducer.toggleGroupItemActiveState(item)
    }

    fun deleteGroupItem(item: GroupItem) {
        reducer.deleteGroupItem(item)
    }

    fun backButtonCallback() {
        navigation.navigateTo(AppScreens.PopBackScreen)
    }

    fun showAddGroupItemScreen() {
        showAddItem.postValue(true)
    }

    fun hideAddGroupItemScreen() {
        showAddItem.postValue(false)
    }

    fun hideRandomItemMenu() {
        randomItem.postValue(null)
    }

//    private fun updateState(newState: GroupScreen.State) {
//        randomButtonEnabled.postValue(newState.randomEnabled)
//        groupsItemsList.postValue(newState.group.items)
//        selectedGroupId.postValue(newState.group.id)
//    }
}
