package com.nikolai.ihavepaws.localStorage.sqldelight

import android.content.Context
import com.nikolai.GroupsDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver = AndroidSqliteDriver(
        GroupsDatabase.Schema,
        context, "groups.db"
    )
}