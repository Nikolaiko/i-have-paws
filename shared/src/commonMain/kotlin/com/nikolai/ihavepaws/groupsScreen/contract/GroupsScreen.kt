package com.nikolai.ihavepaws.groupsScreen.contract

import com.nikolai.ihavepaws.model.Group
import com.nikolai.ihavepaws.model.StateMessage
import com.nikolai.ihavepaws.model.flowsProxy.AnyFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

class GroupsScreen {
    data class State(
        val groups: List<Group> = emptyList()
    )

    interface Reducer {
        val state: AnyFlow<State>
        val messages: AnyFlow<StateMessage>

        fun refreshGroupsList()
        fun removeGroup(groupName: String)
    }
}