package com.nikolai.ihavepaws.addGroupScreen.contract

import com.nikolai.ihavepaws.model.base.BaseReducer

class AddGroup {
    interface Reducer : BaseReducer {
        fun addNewGroup(id: String, name: String)
    }
}