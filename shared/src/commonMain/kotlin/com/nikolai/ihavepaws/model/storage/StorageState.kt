package com.nikolai.ihavepaws.model.storage

import com.nikolai.ihavepaws.model.consts.errorDescription
import com.nikolai.ihavepaws.model.consts.notInitializedDescription
import com.nikolai.ihavepaws.model.consts.readyDescription

sealed class StorageState(
    val description: String
) {
    object NotInitialized : StorageState(notInitializedDescription)
    object Ready : StorageState(readyDescription)
    object FailedWithError : StorageState(errorDescription)
}
