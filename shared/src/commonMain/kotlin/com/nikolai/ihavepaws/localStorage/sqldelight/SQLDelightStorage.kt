package com.nikolai.ihavepaws.localStorage.sqldelight

import com.nikolai.GroupsDatabase
import com.nikolai.ihavepaws.localStorage.LocalStorage
import com.nikolai.ihavepaws.localStorage.exceptions.LocalStorageException
import com.nikolai.ihavepaws.model.Group
import com.nikolai.ihavepaws.model.GroupItem
import com.nikolai.ihavepaws.model.storage.StorageState

class SQLDelightStorage constructor(
    private val factory: DatabaseDriverFactory
): LocalStorage {
    override val state: StorageState
        get() = _state

    private var _state: StorageState = StorageState.NotInitialized
    private val database = GroupsDatabase(factory.createDriver())
    private val queries = database.groupsTableQueries

    init {
        _state = StorageState.Ready
    }

    override fun addNewGroup(newGroup: Group): Result<Group> {
        val result = queries.selectGroupByName(newGroup.name).executeAsList()
        return when(result.isEmpty()) {
            false -> Result.failure(LocalStorageException.ObjectAlreadyExists)
            true -> {
                queries.addGroup(newGroup.id, newGroup.name)
                Result.success(newGroup)
            }
        }
    }

    override fun getAllGroups(): List<Group>
        = queries.selectAllGroups(::convertToGroup).executeAsList()

    override fun addNewItemToGroup(groupId: String, item: GroupItem): Result<GroupItem> {
        TODO("Not yet implemented")
    }

    private fun convertToGroup(id: String, name: String)
        = Group(id = id, name = name, items = emptyList())
}