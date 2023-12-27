package com.nikolai.ihavepaws.addGroupScreen.reducer

import com.nikolai.ihavepaws.addGroupScreen.contract.AddGroup
import com.nikolai.ihavepaws.services.localStorage.LocalStorage
import com.nikolai.ihavepaws.model.Group
import com.nikolai.ihavepaws.model.MessageCallback
import com.nikolai.ihavepaws.model.StateMessage
import com.nikolai.ihavepaws.model.consts.addGroupError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow

class AddGroupReducer constructor(
    private val storage: LocalStorage
): AddGroup.Reducer {
    private val messagesFlow = MutableSharedFlow<StateMessage>()

    override var messagesCallback: MessageCallback? = null

    private val job = Job()
    private var scope = CoroutineScope(Dispatchers.Main + job)

    override fun addNewGroup(id: String, name: String) {
        val result = storage.addNewGroup(Group(id, name, emptyList()))
        val resultMessage = when(result.isSuccess) {
            true -> StateMessage.SuccessMessage
            false -> StateMessage.ErrorMessage(
                result.exceptionOrNull()?.message ?: addGroupError
            )
        }
        emitMessage(resultMessage)
    }

    private fun emitMessage(message: StateMessage) {
        messagesCallback?.invoke(message)
    }
}