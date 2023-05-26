package com.nikolai.ihavepaws.addGroupItemScreen.reducer

import com.nikolai.ihavepaws.addGroupItemScreen.contract.AddGroupItem
import com.nikolai.ihavepaws.localStorage.LocalStorage
import com.nikolai.ihavepaws.model.GroupItem
import com.nikolai.ihavepaws.model.MessageCallback
import com.nikolai.ihavepaws.model.StateMessage
import com.nikolai.ihavepaws.model.consts.addGroupItemError
import com.nikolai.ihavepaws.model.consts.minEntityNameLength
import com.nikolai.ihavepaws.model.consts.shortGroupItemsName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow

class AddGroupItemReducer constructor(
    private val storage: LocalStorage
): AddGroupItem.Reducer {
    override var messagesCallback: MessageCallback? = null

    private val messagesFlow = MutableSharedFlow<StateMessage>()

    private val job = Job()
    private var scope = CoroutineScope(Dispatchers.Main + job)

    override fun addGroupItem(groupId: String, item: GroupItem) {
        when(item.title.length >= minEntityNameLength) {
            true -> {
                val result = storage.addNewItemToGroup(groupId, item)
                result.onSuccess {
                    val message = StateMessage.SuccessMessage
                    emitMessage(message)
                }
                result.onFailure {
                    val message = StateMessage.ErrorMessage(it.message ?: addGroupItemError)
                    emitMessage(message)
                }
            }
            false -> emitMessage(StateMessage.ErrorMessage(shortGroupItemsName))
        }
    }

    private fun emitMessage(message: StateMessage) {
        messagesCallback?.invoke(message)
    }
}