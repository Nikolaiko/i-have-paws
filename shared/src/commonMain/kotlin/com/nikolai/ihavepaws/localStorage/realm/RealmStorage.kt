package com.nikolai.ihavepaws.localStorage.realm

import com.nikolai.ihavepaws.localStorage.LocalStorage
import com.nikolai.ihavepaws.localStorage.exceptions.LocalStorageException
import com.nikolai.ihavepaws.localStorage.realm.extensions.toGroup
import com.nikolai.ihavepaws.localStorage.realm.extensions.toRealmItem
import com.nikolai.ihavepaws.localStorage.realm.model.RealmGroup
import com.nikolai.ihavepaws.localStorage.realm.model.RealmGroupItem
import com.nikolai.ihavepaws.model.Group
import com.nikolai.ihavepaws.model.GroupItem
import com.nikolai.ihavepaws.model.storage.StorageState
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.query

class RealmStorage : LocalStorage {
    private var realm: Realm? = null

    override val state: StorageState
        get() = _state

    private var _state: StorageState = StorageState.NotInitialized()

    init {
        val config = RealmConfiguration.Builder(
            schema = setOf(RealmGroup::class, RealmGroupItem::class)
        ).build()

        try {
            realm = Realm.open(config)
            _state = StorageState.Ready()
        } catch (wrongConfig: IllegalArgumentException) {
            _state = StorageState.FailedWithError()
        }
    }

    override fun addNewGroup(newGroup: Group): Result<Group> {
        val result = realm!!.query<RealmGroup>("name == $0", newGroup.name).find()
        return when(result.isEmpty()) {
            true -> {
                realm!!.writeBlocking {
                    val realmGroup = newGroup.toRealmItem()
                    copyToRealm(realmGroup)
                }
                Result.success(newGroup)
            }
            false -> {
                Result.failure(LocalStorageException.ObjectAlreadyExists)
            }
        }
    }

    override fun getAllGroups(): List<Group> {
        val result = realm!!.query(RealmGroup::class).find()
        return result.map {
            it.toGroup()
        }.toList()
    }

    override fun addNewItemToGroup(
        groupId: String,
        item: GroupItem
    ): Result<GroupItem> {
        val result = realm!!.query<RealmGroup>("id = $0", groupId).find()
        return when(result.isEmpty()) {
            true -> Result.failure(LocalStorageException.ObjectAlreadyExists)
            false -> {
                realm!!.writeBlocking {
                    val group = result.first()
                    val realmItem = item.toRealmItem()
                    group.items.add(realmItem)
                }
                Result.success(item)
            }
        }
    }
}