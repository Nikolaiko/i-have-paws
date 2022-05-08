package com.nikolai.ihavepaws.android.features.addGroupScreen.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikolai.ihavepaws.addGroupScreen.contract.AddGroup
import com.nikolai.ihavepaws.android.features.addGroupScreen.model.AddEntityScreenState
import com.nikolai.ihavepaws.android.model.ViewModelMessage
import com.nikolai.ihavepaws.model.StateMessage
import com.nikolai.ihavepaws.model.consts.minEntityNameLength
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.util.*

class AddGroupViewModel constructor(
    private val reducer: AddGroup.Reducer
) : ViewModel() {

    private val groupNameState = MutableLiveData("")
    private val addButtonState = MutableLiveData(false)
    private val messages = MutableSharedFlow<ViewModelMessage>()

    val state = AddEntityScreenState(
        entityName = groupNameState,
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

    fun resetViewModel() {
        groupNameState.postValue("")
        addButtonState.postValue(false)
    }

    fun onGroupNameChange(newValue: String) {
        groupNameState.postValue(newValue)
        addButtonState.postValue(newValue.length >= minEntityNameLength)
    }

    fun addGroup() {
        reducer.addNewGroup(UUID.randomUUID().toString() , groupNameState.value ?: "")
    }
}
