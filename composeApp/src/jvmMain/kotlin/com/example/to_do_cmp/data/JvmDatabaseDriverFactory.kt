package com.example.to_do_cmp.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.example.to_do_cmp.database.TaskDatabase
import java.util.Properties

class JvmDatabaseDriverFactory : DatabaseDriverFactory {
    override fun createDriver(): SqlDriver {
        val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:task.db",
            Properties(), TaskDatabase.Schema)
        return driver
    }
}