package com.example.to_do_cmp

import androidx.compose.ui.window.ComposeUIViewController
import com.example.to_do_cmp.di.initializeKoin

fun MainViewController() = ComposeUIViewController(
    // init in iOS app, null for the moment
    configure = { initializeKoin() }
) { App() }