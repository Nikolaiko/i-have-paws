package com.nikolai.ihavepaws.localStorage.exceptions

import com.nikolai.ihavepaws.model.consts.objectAlreadyExistsMessage

sealed class LocalStorageException(localMessage: String) : Throwable(localMessage) {
    object ObjectAlreadyExists : LocalStorageException(objectAlreadyExistsMessage)
}
