package com.nikolai.ihavepaws.localStorage

import com.nikolai.ihavepaws.model.Group
import com.nikolai.ihavepaws.model.storage.StorageState
import kotlinx.coroutines.flow.Flow

interface LocalStorage {
    val state: StorageState

    fun getAllGroups(): List<Group>
}