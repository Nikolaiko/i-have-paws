package com.nikolai.ihavepaws.services.localStorage.sqldelight

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.nikolai.GroupsDatabase

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver = AndroidSqliteDriver(
        GroupsDatabase.Schema,
        context,
        "groups.db"
    )
}