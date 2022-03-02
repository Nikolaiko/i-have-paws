package com.nikolai.ihavepaws.model.storage

import com.nikolai.ihavepaws.model.consts.errorDescription
import com.nikolai.ihavepaws.model.consts.notInitializedDescription
import com.nikolai.ihavepaws.model.consts.readyDescription

sealed class StorageState(
    val description: String
) {
    class NotInitialized : StorageState(notInitializedDescription)
    class Ready : StorageState(readyDescription)
    class FailedWithError : StorageState(errorDescription)
}
