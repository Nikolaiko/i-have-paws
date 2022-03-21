package com.nikolai.ihavepaws.groupsScreen.contract

import com.nikolai.ihavepaws.model.Group

class GroupsScreen {
    data class State(
        val groups: List<Group> = emptyList()
    )
}