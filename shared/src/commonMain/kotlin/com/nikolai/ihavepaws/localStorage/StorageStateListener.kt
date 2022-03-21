package com.nikolai.ihavepaws.localStorage

import com.nikolai.ihavepaws.model.storage.StorageState

interface StorageStateListener {
    fun onStorageStateChange(newState: StorageState)
}