package com.nikolai.ihavepaws.groupScreen.contract

import com.nikolai.ihavepaws.model.Group
import com.nikolai.ihavepaws.model.GroupItem
import com.nikolai.ihavepaws.model.GroupScreenCallback
import com.nikolai.ihavepaws.model.base.BaseReducer

class GroupScreen {
    data class State(
        val group: Group = Group("", "", emptyList()),
        val randomEnabled: Boolean = false
    )

    interface Reducer : BaseReducer {
        var callback: GroupScreenCallback?

        fun getGroupByName(name: String)
        fun deleteGroupItem(item: GroupItem)
        fun toggleGroupItemActiveState(groupItem: GroupItem)
        fun selectRandomElement()
    }
}