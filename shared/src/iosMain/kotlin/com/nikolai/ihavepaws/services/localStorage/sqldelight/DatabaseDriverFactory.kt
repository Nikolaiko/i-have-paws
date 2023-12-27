package com.nikolai.ihavepaws.services.localStorage.sqldelight

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.nikolai.GroupsDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(GroupsDatabase.Schema, "groups.db")
    }
}