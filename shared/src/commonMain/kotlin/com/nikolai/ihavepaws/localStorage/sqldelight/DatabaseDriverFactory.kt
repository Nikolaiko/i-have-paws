package com.nikolai.ihavepaws.localStorage.sqldelight

import app.cash.sqldelight.db.SqlDriver


expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}