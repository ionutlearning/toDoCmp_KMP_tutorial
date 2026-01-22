package com.example.to_do_cmp.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    object Home : Screen()
    @Serializable
    data class Task(val id: String? = null) : Screen()
}