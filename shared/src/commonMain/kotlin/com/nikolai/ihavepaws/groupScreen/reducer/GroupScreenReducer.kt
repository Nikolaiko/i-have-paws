package com.nikolai.ihavepaws.groupScreen.reducer

import com.nikolai.ihavepaws.groupScreen.contract.GroupScreen
import com.nikolai.ihavepaws.localStorage.LocalStorage
import com.nikolai.ihavepaws.model.Group
import com.nikolai.ihavepaws.model.GroupItem
import com.nikolai.ihavepaws.model.StateMessage
import com.nikolai.ihavepaws.model.consts.*
import com.nikolai.ihavepaws.model.extensions.isEnabledForRandom
import com.nikolai.ihavepaws.model.extensions.wrapToAny
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class GroupScreenReducer constructor(
    private val storage: LocalStorage
): GroupScreen.Reducer {
    private var currentState = GroupScreen.State()
    private val stateFlow = MutableSharedFlow<GroupScreen.State>()
    private val messagesFlow = MutableSharedFlow<StateMessage>()

    override val state = stateFlow.wrapToAny()
    override val messages = messagesFlow.wrapToAny()

    private val job = Job()
    private var scope = CoroutineScope(Dispatchers.Main + job)

    override fun getGroup(group: Group) {
        val result = storage.getGroupByName(group.name)
        result.onSuccess {
            currentState = currentState.copy(
                group = it,
                randomEnabled = it.isEnabledForRandom()
            )
            emitState()
        }
        result.onFailure {
            val message = StateMessage.ErrorMessage(it.message ?: getGroupItemsError)
            emitMessage(message)
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
        val activeItems = currentState.group.items.filter { item -> item.active }
        val selectedItem = activeItems.random()
        emitMessage(StateMessage.SelectedItemMessage(selectedItem))
    }

    private fun emitState() {
        scope.launch {
            stateFlow.emit(currentState)
        }
    }

    private fun emitMessage(message: StateMessage) {
        scope.launch {
            messagesFlow.emit(message)
        }
    }
}