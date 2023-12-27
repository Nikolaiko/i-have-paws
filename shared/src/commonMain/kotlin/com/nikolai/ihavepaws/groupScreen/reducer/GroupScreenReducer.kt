package com.nikolai.ihavepaws.groupScreen.reducer

import com.nikolai.ihavepaws.groupScreen.contract.GroupScreen
import com.nikolai.ihavepaws.services.localStorage.LocalStorage
import com.nikolai.ihavepaws.model.GroupItem
import com.nikolai.ihavepaws.model.GroupScreenCallback
import com.nikolai.ihavepaws.model.MessageCallback
import com.nikolai.ihavepaws.model.StateMessage
import com.nikolai.ihavepaws.model.consts.deleteGroupItemsError
import com.nikolai.ihavepaws.model.consts.getGroupItemsError
import com.nikolai.ihavepaws.model.consts.setGroupItemActiveStateError
import com.nikolai.ihavepaws.model.extensions.isEnabledForRandom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow

class GroupScreenReducer constructor(
    private val storage: LocalStorage
): GroupScreen.Reducer {
    private var currentState = GroupScreen.State()
    private val stateFlow = MutableSharedFlow<GroupScreen.State>()
    private val messagesFlow = MutableSharedFlow<StateMessage>()

    override var callback: GroupScreenCallback? = null
    override var messagesCallback: MessageCallback? = null

    private val job = Job()
    private var scope = CoroutineScope(Dispatchers.Main + job)

    override fun getGroupByName(name: String) {
        val result = storage.getGroupByName(name)
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
            getGroupByName(currentState.group.name)
        }
        result.onFailure {
            val message = StateMessage.ErrorMessage(it.message ?: deleteGroupItemsError)
            emitMessage(message)
        }
    }

    override fun toggleGroupItemActiveState(groupItem: GroupItem) {
        val result = storage.updateGroupItemActiveState(groupItem.id, !groupItem.active)
        result.onSuccess {
            getGroupByName(currentState.group.name)
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
        callback?.invoke(currentState)
    }

    private fun emitMessage(message: StateMessage) {
        messagesCallback?.invoke(message)
    }
}