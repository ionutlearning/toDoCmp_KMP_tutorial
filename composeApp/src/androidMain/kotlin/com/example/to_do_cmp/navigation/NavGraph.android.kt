package com.example.to_do_cmp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.example.to_do_cmp.presentation.screen.home.HomeScreen
import org.koin.compose.koinInject

@Composable
actual fun NavGraph() {
    // inject with koin
    val navigator = koinInject<Navigator>()

    NavDisplay(
        backStack = navigator.backStack,
        onBack = { navigator.goBack() },
        entryProvider = entryProvider {
            entry<Screen.Home> {
                HomeScreen(
                    navigateToTask = { taskId ->
                        navigator.navigateTo(Screen.Task(taskId))
                    }
                )
            }

            entry<Screen.Task> {

            }
        }
    )
}