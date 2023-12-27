package com.nikolai.ihavepaws.services.localStorage.stub

import com.nikolai.ihavepaws.services.localStorage.LocalStorage
import com.nikolai.ihavepaws.services.localStorage.exceptions.LocalStorageException
import com.nikolai.ihavepaws.model.Group
import com.nikolai.ihavepaws.model.GroupItem
import com.nikolai.ihavepaws.model.storage.StorageState

class StubStorage: LocalStorage {
    override val state: StorageState
        get() = StorageState.Ready

    override fun getAllGroups(): List<Group> {
        return emptyList()
    }

    override fun getGroupByName(name: String): Result<Group> {
        return Result.failure(LocalStorageException.ObjectDoesNotExists)
    }

    override fun removeGroupByName(name: String): Result<Boolean> {
        return Result.failure(LocalStorageException.ObjectDoesNotExists)
    }

    override fun addNewGroup(newGroup: Group): Result<Group> {
        return Result.failure(LocalStorageException.ObjectDoesNotExists)
    }

    override fun addNewItemToGroup(groupId: String, item: GroupItem): Result<GroupItem> {
        return Result.failure(LocalStorageException.ObjectDoesNotExists)
    }

    override fun deleteGroupItemById(groupItemId: String): Result<Boolean> {
        return Result.failure(LocalStorageException.ObjectDoesNotExists)
    }

    override fun updateGroupItemActiveState(groupItemId: String, active: Boolean): Result<Boolean> {
        return Result.failure(LocalStorageException.ObjectDoesNotExists)
    }
}