package com.example.to_do_cmp.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.example.to_do_cmp.database.TaskDatabase
import com.example.to_do_cmp.domain.Priority
import com.example.to_do_cmp.domain.ToDoTask
import com.example.to_do_cmp.util.RequestState
import com.example.todocmp.database.TaskTable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlin.coroutines.CoroutineContext
import kotlin.time.Clock

class ToDoRepositoryImpl(
    databaseDriverFactory: DatabaseDriverFactory
) : ToDoRepository {

    private val db = TaskDatabase(databaseDriverFactory.createDriver())
    private val query = db.taskDatabaseQueries

    override fun createTask(task: ToDoTask): RequestState<Unit> {
        return try {
            query.insertTask(
                id = task.id,
                title = task.title,
                description = task.description,
                isCompleted = if (task.isCompleted) 1 else 0,
                priority = task.priority.name,
                created_at = task.createdAt,
                updated_at = task.updatedAt
            )

            RequestState.Success(Unit)

        } catch (e: Exception) {
            RequestState.Error("DB Error: ${e.message}")
        }
    }

    override fun updateTask(task: ToDoTask): RequestState<Unit> {
        return try {
            query.updateTask(
                id = task.id,
                title = task.title,
                description = task.description,
                isCompleted = if (task.isCompleted) 1 else 0,
                priority = task.priority.name,
                updated_at = Clock.System.now().toEpochMilliseconds()
            )

            RequestState.Success(Unit)

        } catch (e: Exception) {
            RequestState.Error("DB Error: ${e.message}")
        }
    }

    override fun readSelectedTask(taskId: String): RequestState<ToDoTask> {
        return try {
            val task = query.selectTaskById(taskId).executeAsOneOrNull()
            task?.let {
                RequestState.Success(it.convert())
            } ?: RequestState.Error("Task not found")

        } catch (e: Exception) {
            RequestState.Error("DB Error: ${e.message}")
        }
    }

    override fun readAllTasks(context: CoroutineContext): Flow<RequestState<List<ToDoTask>>> {
        return query.selectAllTasks()
            .asFlow()
            .mapToList(context)
            .map { tasks ->
                RequestState.Success(tasks.map { task -> task.convert() })
            }
            .catch {
                RequestState.Error("DB Error: ${it.message}")
            }
    }

    override fun removeTask(taskId: String): RequestState<Unit> {
        return try {
            query.deleteTaskById(taskId)
            RequestState.Success(Unit)

        } catch (e: Exception) {
            RequestState.Error("DB Error: ${e.message}")
        }
    }

    private fun TaskTable.convert(): ToDoTask {
        return ToDoTask(
            id = id,
            title = title,
            description = description,
            isCompleted = isCompleted == 1L,
            priority = Priority.valueOf(priority),
            createdAt = created_at,
            updatedAt = updated_at
        )
    }

}