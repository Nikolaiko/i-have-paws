package com.nikolai.ihavepaws.services.localStorage.realm

import com.nikolai.ihavepaws.services.localStorage.LocalStorage
import com.nikolai.ihavepaws.services.localStorage.ObservableLocalStorage
import com.nikolai.ihavepaws.services.localStorage.realm.dbObjects.RandomGroup
import com.nikolai.ihavepaws.model.Group
import com.nikolai.ihavepaws.model.GroupItem
import com.nikolai.ihavepaws.model.storage.StorageState
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.find
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.random.Random

class RealmStorage(
    private val config: RealmConfiguration
): LocalStorage, ObservableLocalStorage {
    private var _state: StorageState = StorageState.NotInitialized
    override val state: StorageState
        get() = _state

    private val job = Job()
    private var scope = CoroutineScope(Dispatchers.IO + job)

    private var realm: Realm = Realm.open(config)

    override fun getAllGroups(): List<Group> {
        val results = realm.query(RandomGroup::class).find()
        return results.map {
            Group(
                id = it.id,
                name = it.name,
                items = emptyList()
            )
        }
    }

    override fun getGroupByName(name: String): Result<Group> {
        TODO("Not yet implemented")
    }

    override fun removeGroupByName(name: String): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override fun addNewGroup(newGroup: Group): Result<Group> {
        return kotlin.runCatching {
            realm.writeBlocking {
                copyToRealm(RandomGroup().apply {
                    id = newGroup.id
                    name = newGroup.name
                })
                newGroup
            }
        }
    }

    override fun addNewItemToGroup(groupId: String, item: GroupItem): Result<GroupItem> {
        TODO("Not yet implemented")
    }

    override fun deleteGroupItemById(groupItemId: String): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override fun updateGroupItemActiveState(groupItemId: String, active: Boolean): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override fun getGroupsFlow(): Flow<List<Group>> = realm.query(RandomGroup::class).asFlow().map {
        it.list.map {
            Group(
                id = it.id,
                name = it.name,
                items = emptyList()
            )
        }
    }

    override fun addRandomGroup(newGroup: Group) {
        scope.launch {
            realm.write {
                copyToRealm(RandomGroup().apply {
                    id = newGroup.id
                    name = newGroup.name
                })
            }
        }
    }
}