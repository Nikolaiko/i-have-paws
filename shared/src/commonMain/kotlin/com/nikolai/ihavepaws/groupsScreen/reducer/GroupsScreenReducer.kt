package com.nikolai.ihavepaws.groupsScreen.reducer

import com.nikolai.ihavepaws.groupsScreen.contract.GroupsScreen
import com.nikolai.ihavepaws.localStorage.LocalStorage
import com.nikolai.ihavepaws.model.StateMessage
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

    private fun emitState() {
        scope.launch {
            state.emit(currentState)
        }
    }
}