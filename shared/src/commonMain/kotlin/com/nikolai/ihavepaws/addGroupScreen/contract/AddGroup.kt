package com.nikolai.ihavepaws.addGroupScreen.contract

import com.nikolai.ihavepaws.groupsScreen.contract.GroupsScreen
import com.nikolai.ihavepaws.model.StateMessage
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

class AddGroup {
    interface Reducer {
        val messages: SharedFlow<StateMessage>

        fun addNewGroup(id: String, name: String)
    }
}