package com.nikolai.ihavepaws.groupsScreen.contract

import com.nikolai.ihavepaws.model.Group
import com.nikolai.ihavepaws.model.GroupsScreenCallback
import com.nikolai.ihavepaws.model.base.BaseReducer

class GroupsScreen {
    data class State(
        val groups: List<Group> = emptyList()
    )

    interface Reducer: BaseReducer {
        var callback: GroupsScreenCallback?
        fun refreshGroupsList()
        fun removeGroup(groupName: String)
    }
}