package com.nikolai.ihavepaws.navigation.model

import androidx.compose.runtime.Composable

data class Screen(
    val id: String,
    val view: ComposableView
) {
    override fun equals(other: Any?): Boolean {
        return other is Screen && this.id == other.id
    }

    override fun hashCode() = id.hashCode()
}
