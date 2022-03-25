package com.nikolai.ihavepaws.addGroupScreen.reducer

import com.nikolai.ihavepaws.addGroupScreen.contract.AddGroup
import com.nikolai.ihavepaws.localStorage.LocalStorage
import com.nikolai.ihavepaws.model.Group
import com.nikolai.ihavepaws.model.StateMessage
import com.nikolai.ihavepaws.model.consts.failMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class AddGroupReducer constructor(
    private val storage: LocalStorage
): AddGroup.Reducer {

    override val messages = MutableSharedFlow<StateMessage>()

    private val job = Job()
    private var scope = CoroutineScope(Dispatchers.Main + job)

    override fun addNewGroup(id: String, name: String) {
        val result = storage.addNewGroup(Group(id, name, emptyList()))
        val resultMessage = when(result.isSuccess) {
            true -> StateMessage.SuccessMessage
            false -> StateMessage.ErrorMessage(
                result.exceptionOrNull()?.message ?: failMessage
            )
        }
        emitMessage(resultMessage)
    }

    private fun emitMessage(message: StateMessage) {
        scope.launch {
            messages.emit(message)
        }
    }
}