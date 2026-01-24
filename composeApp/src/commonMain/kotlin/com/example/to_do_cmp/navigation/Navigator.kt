package com.example.to_do_cmp.navigation

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

class Navigator {

    val backStack: SnapshotStateList<Screen> = mutableStateListOf(Screen.Home)

    fun navigateToTask(taskId: String? = null) {
        if (backStack.lastOrNull() is Screen.Task) {
            backStack[backStack.lastIndex] = Screen.Task(id = taskId)
        } else {
            backStack.add(Screen.Task(id = taskId))
        }
    }

    fun goBack() {
        backStack.removeLastOrNull()
    }
}