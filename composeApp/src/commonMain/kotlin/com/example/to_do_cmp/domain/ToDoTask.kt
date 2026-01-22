package com.example.to_do_cmp.domain

import kotlin.time.Clock
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class ToDoTask(
    val id: String = Uuid.random().toHexString(),
    val title: String,
    val description: String,
    val isCompleted: Boolean = false,
    val priority: Priority = Priority.None,
    val createdAt: Long = Clock.System.now().toEpochMilliseconds(),
    val updatedAt: Long = 0L
)

enum class Priority {
    None,
    Low,
    Medium,
    High
}
