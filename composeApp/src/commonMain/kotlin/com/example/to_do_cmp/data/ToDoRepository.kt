package com.example.to_do_cmp.data

import com.example.to_do_cmp.domain.ToDoTask
import com.example.to_do_cmp.util.RequestState
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

interface ToDoRepository {

    fun createTask(task: ToDoTask): RequestState<Unit>

    fun updateTask(task: ToDoTask): RequestState<Unit>

    fun readSelectedTask(taskId: String): RequestState<ToDoTask>

    fun readAllTasks(context: CoroutineContext? = null): Flow<RequestState<List<ToDoTask>>>

    fun removeTask(taskId: String): RequestState<Unit>

}