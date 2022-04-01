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

    override fun addNewItemToGroup(groupId: String, item: GroupItem): Result<GroupItem> {
        return try {
            queries.addGroupItem(item.id, item.title, groupId)
            Result.success(item)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    override fun getAllGroups(): List<Group>
        = queries.selectAllGroups(::convertToGroup).executeAsList()

    override fun getGroupByName(name: String): Result<Group> {
        val group = queries.selectGroupByName(name, ::convertToGroup).executeAsOneOrNull()
        return when (group != null) {
            false -> Result.failure(LocalStorageException.ObjectDoesNotExists)
            true -> {
                val items = queries
                    .selectGroupItemsByGroupId(group.id, ::convertToGroupItem)
                    .executeAsList()
                Result.success(group.copy(items = items))
            }
        }
    }

    private fun convertToGroup(id: String, name: String)
        = Group(id = id, name = name, items = emptyList())

    private fun convertToGroupItem(id: String, groupId: String, name: String)
        = GroupItem(id = id, title = name)
}