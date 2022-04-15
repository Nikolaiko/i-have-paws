package com.nikolai.ihavepaws.localStorage

import com.nikolai.ihavepaws.model.Group
import com.nikolai.ihavepaws.model.GroupItem
import com.nikolai.ihavepaws.model.storage.StorageState

interface LocalStorage {
    val state: StorageState

    fun getAllGroups(): List<Group>
    fun getGroupByName(name: String): Result<Group>
    fun removeGroupByName(name: String): Result<Boolean>

    fun addNewGroup(newGroup: Group): Result<Group>

    fun addNewItemToGroup(groupId: String, item: GroupItem): Result<GroupItem>
    fun updateGroupItemActiveState(groupItemId: String, active: Boolean): Result<Boolean>
}