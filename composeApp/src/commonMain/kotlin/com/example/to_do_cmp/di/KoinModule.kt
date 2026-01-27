package com.example.to_do_cmp.di

import com.example.to_do_cmp.data.ToDoRepository
import com.example.to_do_cmp.data.ToDoRepositoryImpl
import com.example.to_do_cmp.navigation.Navigator
import com.example.to_do_cmp.presentation.screen.home.HomeViewModel
import com.example.to_do_cmp.presentation.screen.task.TaskViewModel
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

expect val databaseModule: Module
val koinModule = module {
    // create singleton
    singleOf(constructor = ::Navigator)
//    single<ToDoRepository> { FakeToDoRepository() }   // needed before db just to have some data
    single<ToDoRepository> { ToDoRepositoryImpl(get()) }

    viewModelOf(::HomeViewModel)
    viewModelOf(::TaskViewModel)
}

// config for Android for the moment
fun initializeKoin(
    config: (KoinApplication. () -> Unit)? = null
) {
    startKoin {
        config?.invoke(this)            // pass android context to start
        modules(
            modules = listOf(
                koinModule,
                databaseModule
            )
        )   // register modules
    }
}