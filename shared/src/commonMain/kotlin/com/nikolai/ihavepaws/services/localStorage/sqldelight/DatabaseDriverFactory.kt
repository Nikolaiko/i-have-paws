package com.nikolai.ihavepaws.services.localStorage.sqldelight

import app.cash.sqldelight.db.SqlDriver


expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}