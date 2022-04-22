package com.nikolai.ihavepaws.addGroupItemScreen.contract

import com.nikolai.ihavepaws.model.GroupItem
import com.nikolai.ihavepaws.model.base.BaseReducer

class AddGroupItem {
    interface Reducer : BaseReducer {
        fun addGroupItem(groupId: String, item: GroupItem)
    }
}