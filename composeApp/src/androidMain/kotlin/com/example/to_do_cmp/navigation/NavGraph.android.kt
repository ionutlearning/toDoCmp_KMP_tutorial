package com.example.to_do_cmp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay

@Composable
actual fun NavGraph() {

    val navigator = remember { Navigator() }

    NavDisplay(
        backStack = navigator.backStack,
        onBack = { navigator.goBack() },
        entryProvider = entryProvider {
            entry<Screen.Home> {

            }

            entry<Screen.Task> {

            }
        }
    )
}