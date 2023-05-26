package com.nikolai.ihavepaws.groupsScreen.reducer

import com.nikolai.ihavepaws.groupsScreen.contract.GroupsScreen
import com.nikolai.ihavepaws.localStorage.LocalStorage
import com.nikolai.ihavepaws.model.GroupsScreenCallback
import com.nikolai.ihavepaws.model.MessageCallback
import com.nikolai.ihavepaws.model.StateMessage
import com.nikolai.ihavepaws.model.consts.deleteGroupError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

class GroupsScreenReducer constructor (
    private val storage: LocalStorage
): GroupsScreen.Reducer {
    private var currentState = GroupsScreen.State()

    private val stateFlow = MutableStateFlow(currentState)
    private val messageFlow = MutableSharedFlow<StateMessage>()

    override var callback: GroupsScreenCallback? = null
    override var messagesCallback: MessageCallback? = null

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
        callback?.invoke(currentState)
    }

    private fun emitMessage(message: StateMessage) {
        messagesCallback?.invoke(message)
    }
}