package com.nikolai.ihavepaws.model

import com.nikolai.ihavepaws.model.consts.successMessage

sealed class StateMessage(
    val text: String
) {
    class InfoMessage(text: String) : StateMessage(text)
    class ErrorMessage(text: String) : StateMessage(text)

    object SuccessMessage: StateMessage(successMessage)
}
