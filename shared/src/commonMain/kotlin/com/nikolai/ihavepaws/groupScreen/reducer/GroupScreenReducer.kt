package com.nikolai.ihavepaws.groupScreen.reducer

import com.nikolai.ihavepaws.groupScreen.contract.GroupScreen
import com.nikolai.ihavepaws.localStorage.LocalStorage
import com.nikolai.ihavepaws.model.Group
import com.nikolai.ihavepaws.model.GroupItem
import com.nikolai.ihavepaws.model.StateMessage
import com.nikolai.ihavepaws.model.consts.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class GroupScreenReducer constructor(
    private val storage: LocalStorage
): GroupScreen.Reducer {
    private var currentState = GroupScreen.State()

    override val state = MutableSharedFlow<GroupScreen.State>()
    override val messages = MutableSharedFlow<StateMessage>()

    private val job = Job()
    private var scope = CoroutineScope(Dispatchers.Main + job)

    override fun getGroup(group: Group) {
        val result = storage.getGroupByName(group.name)
        result.onSuccess {
            currentState = currentState.copy(group = it)
            emitState()
        }
        result.onFailure {
            val message = StateMessage.ErrorMessage(it.message ?: getGroupItemsError)
            emitMessage(message)
        }
    }

    override fun addGroupItem(item: GroupItem) {
        when(item.title.length >= 4) {
            true -> {
                val result = storage.addNewItemToGroup(currentState.group.id, item)
                result.onSuccess {
                    getGroup(currentState.group)
                }
                result.onFailure {
                    val message = StateMessage.ErrorMessage(it.message ?: getGroupItemsError)
                    emitMessage(message)
                }
            }
            false -> emitMessage(StateMessage.ErrorMessage(shortGroupItemsName))
        }
    }

    override fun deleteGroupItem(item: GroupItem) {
        val result = storage.deleteGroupItemById(item.id)
        result.onSuccess {
            getGroup(currentState.group)
        }
        result.onFailure {
            val message = StateMessage.ErrorMessage(it.message ?: deleteGroupItemsError)
            emitMessage(message)
        }
    }

    override fun toggleGroupItemActiveState(groupItem: GroupItem) {
        val result = storage.updateGroupItemActiveState(groupItem.id, !groupItem.active)
        result.onSuccess {
            getGroup(currentState.group)
        }
        result.onFailure {
            val message = StateMessage.ErrorMessage(it.message ?: setGroupItemActiveStateError)
            emitMessage(message)
        }
    }

    override fun selectRandomElement() {
        val selectedItem = currentState.group.items.random()
        emitMessage(StateMessage.SelectedItemMessage(selectedItem))
    }

    private fun emitState() {
        scope.launch {
            state.emit(currentState)
        }
    }

    private fun emitMessage(message: StateMessage) {
        scope.launch {
            messages.emit(message)
        }
    }
}