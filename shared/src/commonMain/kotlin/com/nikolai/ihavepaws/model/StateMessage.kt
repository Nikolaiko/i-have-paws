package com.nikolai.ihavepaws.model

sealed class StateMessage(
    val text: String
) {
    class InfoMessage(text: String) : StateMessage(text)
    class ErrorMessage(text: String) : StateMessage(text)
}
