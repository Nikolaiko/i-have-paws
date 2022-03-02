package com.nikolai.ihavepaws.localStorage.realm

import com.nikolai.ihavepaws.localStorage.LocalStorage
import com.nikolai.ihavepaws.localStorage.realm.model.RealmGroup
import com.nikolai.ihavepaws.localStorage.realm.model.RealmGroupItem
import com.nikolai.ihavepaws.model.Group
import com.nikolai.ihavepaws.model.GroupItem
import com.nikolai.ihavepaws.model.storage.StorageState
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

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

    override fun getAllGroups(): List<Group> {
        val queryResult = realm?.query(RealmGroup::class)?.find()
        val groups = mutableListOf<Group>()

        queryResult?.forEach {
            val items = it.items.map { realmItem ->
                GroupItem(realmItem.id, realmItem.title)
            }
            groups.add(Group(it.id, it.name, items))
        }
        return groups
    }
}