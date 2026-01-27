package com.example.to_do_cmp.di

import com.example.to_do_cmp.data.AndroidDatabaseDriverFactory
import com.example.to_do_cmp.data.DatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val databaseModule = module {
    single<DatabaseDriverFactory> { AndroidDatabaseDriverFactory(androidContext()) }
}