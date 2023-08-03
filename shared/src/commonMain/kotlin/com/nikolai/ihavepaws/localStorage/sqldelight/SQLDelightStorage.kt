//package com.nikolai.ihavepaws.localStorage.sqldelight
//
//import com.nikolai.GroupsDatabase
//import com.nikolai.GroupsTable
//import com.nikolai.ihavepaws.localStorage.LocalStorage
//import com.nikolai.ihavepaws.localStorage.exceptions.LocalStorageException
//import com.nikolai.ihavepaws.model.Group
//import com.nikolai.ihavepaws.model.GroupItem
//import com.nikolai.ihavepaws.model.extensions.toBoolean
//import com.nikolai.ihavepaws.model.extensions.toLong
//import com.nikolai.ihavepaws.model.storage.StorageState
//
//class SQLDelightStorage constructor(
//    private val factory: DatabaseDriverFactory
//): LocalStorage {
//    override val state: StorageState
//        get() = _state
//
//    private var _state: StorageState = StorageState.NotInitialized
//    private val database = GroupsDatabase(factory.createDriver())
//    private val queries = database.groupsTableQueries
//
//    init {
//        _state = StorageState.Ready
//    }
//
//    override fun addNewGroup(newGroup: Group): Result<Group> {
//        val result = queries.selectGroupByName(newGroup.name).executeAsOneOrNull()
//        return when(result == null) {
//            false -> Result.failure(LocalStorageException.ObjectAlreadyExists)
//            true -> {
//                queries.addGroup(newGroup.id, newGroup.name)
//                Result.success(newGroup)
//            }
//        }
//    }
//
//    override fun addNewItemToGroup(groupId: String, item: GroupItem): Result<GroupItem> {
//        return try {
//            queries.addGroupItem(item.id, item.title, groupId, item.active.toLong())
//            Result.success(item)
//        } catch (exception: Exception) {
//            Result.failure(exception)
//        }
//    }
//
//    override fun getAllGroups(): List<Group>
//        = queries.selectAllGroups(::convertToGroup).executeAsList()
//
//    override fun getGroupByName(name: String): Result<Group> {
//        val group = queries.selectGroupByName(name, ::convertToGroup).executeAsOneOrNull()
//        return when (group != null) {
//            false -> Result.failure(LocalStorageException.ObjectDoesNotExists)
//            true -> {
//                val items = queries
//                    .selectGroupItemsByGroupId(group.id, ::convertToGroupItem)
//                    .executeAsList()
//                Result.success(group.copy(items = items))
//            }
//        }
//    }
//
//    override fun removeGroupByName(name: String): Result<Boolean> {
//        val group = queries.selectGroupByName(name, ::convertToGroup).executeAsOneOrNull()
//        return when(group == null) {
//            true -> Result.failure(LocalStorageException.ObjectDoesNotExists)
//            false -> {
//                queries.deleteGroupByName(name)
//                Result.success(true)
//            }
//        }
//    }
//
//    override fun deleteGroupItemById(groupItemId: String): Result<Boolean> {
//        return try {
//            queries.deleteGroupItemById(groupItemId)
//            Result.success(true)
//        } catch (exception: Exception) {
//            Result.failure(exception)
//        }
//    }
//
//    override fun updateGroupItemActiveState(groupItemId: String, active: Boolean): Result<Boolean> {
//        val groupItem = queries.getGroupItemById(groupItemId).executeAsOneOrNull()
//        return when(groupItem == null) {
//            true -> Result.failure(LocalStorageException.ObjectDoesNotExists)
//            false -> {
//                val updatedItem = groupItem.copy(active =active.toLong())
//                queries.insertOrReplaceGroupItem(updatedItem)
//                Result.success(true)
//            }
//        }
//    }
//
//    private fun convertToGroup(id: String, name: String)
//        = Group(id = id, name = name, items = emptyList())
//
//    private fun convertToGroupItem(id: String, groupId: String, name: String, active: Long)
//        = GroupItem(id = id, title = name, active = active.toBoolean())
//}