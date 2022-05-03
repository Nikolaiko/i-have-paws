package com.nikolai.ihavepaws.android.model

sealed class ViewModelMessage(
    val message: String
) {
    object Success: ViewModelMessage("Success")
    class Error(errorMessage: String): ViewModelMessage(errorMessage)
    class Info(infoMessage: String): ViewModelMessage(infoMessage)
}
