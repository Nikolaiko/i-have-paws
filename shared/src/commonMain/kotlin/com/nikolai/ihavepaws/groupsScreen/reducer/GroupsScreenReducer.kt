package com.nikolai.ihavepaws.groupsScreen.reducer

import com.nikolai.ihavepaws.groupsScreen.contract.GroupsScreen
import com.nikolai.ihavepaws.localStorage.LocalStorage
import com.nikolai.ihavepaws.model.StateMessage
import com.nikolai.ihavepaws.model.consts.deleteGroupError
import com.nikolai.ihavepaws.model.consts.errorDescription
import com.nikolai.ihavepaws.model.consts.refreshGroupsError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class GroupsScreenReducer constructor (
    private val storage: LocalStorage
): GroupsScreen.Reducer {
    private var currentState = GroupsScreen.State()

    override val state = MutableStateFlow(currentState)
    override val messages = MutableSharedFlow<StateMessage>()

    private val job = Job()
    private var scope = CoroutineScope(Dispatchers.Main + job)

    override fun refreshGroupsList() {
        val groups = storage.getAllGroups()
        currentState = currentState.copy(groups = groups)
        emitState()
    }

    override fun removeGroup(groupName: String) {
        val result = storage.removeGroupByName(groupName)
        when(result.isSuccess) {
            true -> refreshGroupsList()
            false -> {
                val messageText = result.exceptionOrNull()?.message ?: deleteGroupError
                emitMessage(
                    StateMessage.ErrorMessage(messageText)
                )
            }
        }
    }

    private fun emitState() {
        scope.launch {
            state.emit(currentState)
        }
    }

    private fun emitMessage(message: StateMessage) {
        scope.launch {
            messages.tryEmit(message)
        }
    }
}