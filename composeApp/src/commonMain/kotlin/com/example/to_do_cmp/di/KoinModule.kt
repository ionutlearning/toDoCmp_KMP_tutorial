package com.example.to_do_cmp.di

import com.example.to_do_cmp.navigation.Navigator
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val koinModule = module {
    // create singleton
    singleOf(constructor = ::Navigator)
}

// config for Android for the moment
fun initializeKoin(
    config: (KoinApplication. () -> Unit)? = null
) {
    startKoin {
        config?.invoke(this)            // pass android context to start
        modules(modules = koinModule)   // register modules
    }
}