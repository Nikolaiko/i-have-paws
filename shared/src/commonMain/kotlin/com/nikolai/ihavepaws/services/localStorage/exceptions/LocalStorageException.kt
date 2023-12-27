package com.nikolai.ihavepaws.services.localStorage.exceptions

import com.nikolai.ihavepaws.model.consts.objectAlreadyExistsMessage
import com.nikolai.ihavepaws.model.consts.objectDoesNotExistsMessage

sealed class LocalStorageException(localMessage: String) : Throwable(localMessage) {
    object ObjectAlreadyExists : LocalStorageException(objectAlreadyExistsMessage)
    object ObjectDoesNotExists : LocalStorageException(objectDoesNotExistsMessage)
}
