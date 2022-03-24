package com.nikolai.ihavepaws.localStorage.sqldelight

import com.nikolai.GroupsDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(GroupsDatabase.Schema, "groups.db")
    }
}