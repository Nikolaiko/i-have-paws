package com.nikolai.ihavepaws.groupScreen.reducer

import com.nikolai.ihavepaws.groupScreen.contract.GroupScreen
import com.nikolai.ihavepaws.localStorage.LocalStorage
import com.nikolai.ihavepaws.model.Group
import com.nikolai.ihavepaws.model.GroupItem
import com.nikolai.ihavepaws.model.StateMessage
import com.nikolai.ihavepaws.model.consts.failMessage
import com.nikolai.ihavepaws.model.consts.getGroupItemsError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class GroupScreenReducer constructor(
    private val storage: LocalStorage
): GroupScreen.Reducer {
    private var currentState = GroupScreen.State(Group("", "", emptyList()))

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
        val result = storage.addNewItemToGroup(currentState.group.id, item)
        result.onSuccess {
            getGroup(currentState.group)
        }
        result.onFailure {
            val message = StateMessage.ErrorMessage(it.message ?: getGroupItemsError)
            emitMessage(message)
        }
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