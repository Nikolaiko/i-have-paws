package com.nikolai.ihavepaws.services.localStorage

import com.nikolai.ihavepaws.model.Group
import kotlinx.coroutines.flow.Flow

interface ObservableLocalStorage {
    fun getGroupsFlow(): Flow<List<Group>>
    fun addRandomGroup(newGroup: Group)
}