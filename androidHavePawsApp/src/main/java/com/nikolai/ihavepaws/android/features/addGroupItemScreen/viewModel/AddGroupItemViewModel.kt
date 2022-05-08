package com.nikolai.ihavepaws.android.features.addGroupItemScreen.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikolai.ihavepaws.addGroupItemScreen.contract.AddGroupItem
import com.nikolai.ihavepaws.android.features.addGroupScreen.model.AddEntityScreenState
import com.nikolai.ihavepaws.android.model.ViewModelMessage
import com.nikolai.ihavepaws.model.GroupItem
import com.nikolai.ihavepaws.model.StateMessage
import com.nikolai.ihavepaws.model.consts.minEntityNameLength
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.util.*

class AddGroupItemViewModel(
    private val reducer: AddGroupItem.Reducer
) : ViewModel() {

    private val groupItemNameState = MutableLiveData("")
    private val addButtonState = MutableLiveData(false)
    private val messages = MutableSharedFlow<ViewModelMessage>()

    private var selectedGroupId: String = ""

    val state = AddEntityScreenState(
        entityName = groupItemNameState,
        addButtonEnabled = addButtonState,
        messages = messages
    )

    init {
        viewModelScope.launch {
            reducer.messages.collect {
                when(it) {
                    is StateMessage.SuccessMessage -> messages.emit(ViewModelMessage.Success)
                    is StateMessage.ErrorMessage -> messages.emit(ViewModelMessage.Error(it.text))
                    else -> messages.tryEmit(ViewModelMessage.Info(it.text))
                }
            }
        }
    }

    fun initGroup(groupId: String) {
        selectedGroupId = groupId
    }

    fun resetViewModel() {
        groupItemNameState.postValue("")
        addButtonState.postValue(false)
    }

    fun onGroupNameChange(newValue: String) {
        groupItemNameState.postValue(newValue)
        addButtonState.postValue(newValue.length >= minEntityNameLength)
    }

    fun addGroup() {
        val groupItem = GroupItem(
            UUID.randomUUID().toString(),
            groupItemNameState.value ?: "",
            true
        )
        reducer.addGroupItem(selectedGroupId, groupItem)
    }
}
