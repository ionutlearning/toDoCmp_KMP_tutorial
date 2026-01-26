package com.example.to_do_cmp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.to_do_cmp.presentation.screen.home.HomeScreen
import com.example.to_do_cmp.presentation.screen.task.TaskScreen

@Composable
actual fun NavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Home,
    ) {
        composable<Screen.Home> {
            HomeScreen(
                navigateToTask = { taskId ->
                    navController.navigate(Screen.Task(taskId))
                }
            )
        }

        composable<Screen.Task> {
            TaskScreen(
                id = it.toRoute<Screen.Task>().id,
                navigateBack = { navController.navigateUp() }
            )
        }
    }

}