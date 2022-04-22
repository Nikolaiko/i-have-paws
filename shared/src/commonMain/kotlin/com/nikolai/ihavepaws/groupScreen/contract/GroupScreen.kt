package com.nikolai.ihavepaws.groupScreen.contract

import com.nikolai.ihavepaws.model.Group
import com.nikolai.ihavepaws.model.GroupItem
import com.nikolai.ihavepaws.model.StateMessage
import com.nikolai.ihavepaws.model.base.BaseReducer
import com.nikolai.ihavepaws.model.flowsProxy.AnyFlow
import kotlinx.coroutines.flow.SharedFlow

class GroupScreen {
    data class State(
        val group: Group = Group("", "", emptyList()),
        val randomEnabled: Boolean = false
    )

    interface Reducer : BaseReducer {
        val state: AnyFlow<State>

        fun getGroup(group: Group)
        fun deleteGroupItem(item: GroupItem)
        fun toggleGroupItemActiveState(groupItem: GroupItem)
        fun selectRandomElement()
    }
}