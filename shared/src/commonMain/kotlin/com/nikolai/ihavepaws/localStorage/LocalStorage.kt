package com.nikolai.ihavepaws.localStorage

import com.nikolai.ihavepaws.localStorage.realm.model.RealmGroup
import com.nikolai.ihavepaws.model.Group
import com.nikolai.ihavepaws.model.GroupItem
import com.nikolai.ihavepaws.model.storage.StorageState
import kotlinx.coroutines.flow.Flow

interface LocalStorage {
    val state: StorageState

    fun addNewGroup(newGroup: Group): Result<Group>
    fun getAllGroups(): List<Group>

    fun addNewItemToGroup(groupId: String, item: GroupItem): Result<GroupItem>
}