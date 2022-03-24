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

class GroupsScreenReducer constructor (
    private val storage: LocalStorage
): GroupsScreen.Reducer {
    private var currentState = GroupsScreen.State()
    private var scope: CoroutineScope? = null

    override val state = MutableStateFlow(currentState)
    override val messages = MutableSharedFlow<StateMessage>()

    private val job = Job()

    init {
        scope = CoroutineScope(Dispatchers.Main + job)
        scope?.launch {

        }
    }

    override fun refreshGroupsList() {
        scope?.launch {
            val groups = storage!!.getAllGroups()
            currentState = currentState.copy(groups = groups)
            emitState()
        }
    }

    private suspend fun emitErrorMessage(message: String) {
        messages.emit(StateMessage.ErrorMessage(message))
    }

    private suspend fun emitInfoMessage(message: String) {
        messages.emit(StateMessage.InfoMessage(message))
    }

    private suspend fun emitState() {
        state.emit(currentState)
    }
}