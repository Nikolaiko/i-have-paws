package com.nikolai.ihavepaws.groupsScreen.reducer

import com.nikolai.ihavepaws.groupsScreen.contract.GroupsScreen
import com.nikolai.ihavepaws.localStorage.LocalStorage
import com.nikolai.ihavepaws.localStorage.realm.RealmStorage
import com.nikolai.ihavepaws.model.Group
import com.nikolai.ihavepaws.model.GroupItem
import com.nikolai.ihavepaws.model.StateMessage
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.coroutines.CoroutineContext

class GroupsScreenReducer {
    private var currentState = GroupsScreen.State()
    private var scope: CoroutineScope? = null

    private val _state: MutableStateFlow<GroupsScreen.State> = MutableStateFlow(currentState)
    val state: StateFlow<GroupsScreen.State> = _state

    private val _messages: MutableSharedFlow<StateMessage> = MutableSharedFlow()
    val messages: SharedFlow<StateMessage> = _messages

    private var storage: LocalStorage? = null
    private val job = Job()

    init {
        scope = CoroutineScope(Dispatchers.Main + job)
        scope?.launch {
            storage = RealmStorage()
        }
    }

    fun refreshGroupsList() {
        scope?.launch {
            val groups = storage!!.getAllGroups()
            currentState = currentState.copy(groups = groups)
            emitState()
        }
    }

    fun addGroup(newGroup: Group) {
        scope?.launch {
            val result = storage!!.addNewGroup(newGroup)
            when (result.isSuccess) {
                true -> {
                    refreshGroupsList()
                    emitInfoMessage("Group ${newGroup.name} added.")
                }
                false -> emitErrorMessage("Error adding group")
            }
        }
    }

    private suspend fun emitErrorMessage(message: String) {
        _messages.emit(StateMessage.ErrorMessage(message))
    }

    private suspend fun emitInfoMessage(message: String) {
        _messages.emit(StateMessage.InfoMessage(message))
    }

    private suspend fun emitState() {
        _state.emit(currentState)
    }
}