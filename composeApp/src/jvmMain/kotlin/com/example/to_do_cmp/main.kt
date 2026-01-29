package com.example.to_do_cmp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.to_do_cmp.di.initializeKoin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.swing.Swing
import kotlinx.coroutines.test.setMain


@OptIn(ExperimentalCoroutinesApi::class)
fun main() = application {
    initializeKoin { }
    Dispatchers.setMain(Dispatchers.Swing)
    Window(
        onCloseRequest = ::exitApplication,
        title = "To Do CMP"
    ) {
        App()
    }
}