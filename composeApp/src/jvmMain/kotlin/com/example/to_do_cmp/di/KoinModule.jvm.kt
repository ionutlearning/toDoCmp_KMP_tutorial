package com.example.to_do_cmp.di

import com.example.to_do_cmp.data.DatabaseDriverFactory
import com.example.to_do_cmp.data.JvmDatabaseDriverFactory
import org.koin.dsl.module

actual val databaseModule = module {
    single<DatabaseDriverFactory> { JvmDatabaseDriverFactory() }
}