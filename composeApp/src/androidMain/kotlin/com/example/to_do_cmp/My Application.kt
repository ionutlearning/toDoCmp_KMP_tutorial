package com.example.to_do_cmp

import android.app.Application
import com.example.to_do_cmp.di.initializeKoin
import org.koin.android.ext.koin.androidContext

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeKoin(
            config = {
                // pass android context to start koin
                androidContext((this@MyApplication))
            }
        )
    }
}