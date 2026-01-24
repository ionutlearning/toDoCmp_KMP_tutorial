package com.example.to_do_cmp.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_do_cmp.data.ToDoRepository
import com.example.to_do_cmp.domain.ToDoTask
import com.example.to_do_cmp.util.RequestState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    private val repository: ToDoRepository
) : ViewModel() {
    private val _searchQuery = MutableStateFlow<String>("")
    val searchQuery: StateFlow<String> = _searchQuery

    val allTasks = combine(
        repository.readAllTasks(),
        _searchQuery
    ) { tasks, query ->
        when (tasks) {
            is RequestState.Success -> {
                val filteredTasks = tasks.data
                    .let { list ->
                        if (query.isBlank()) list
                        else list.filter {
                            it.title.lowercase().contains(query, ignoreCase = false) ||
                                    it.description.lowercase()
                                        .contains(query, ignoreCase = false)
                        }
                    }
                    .sortedByDescending { it.priority.ordinal }
                RequestState.Success(filteredTasks)
            }

            else -> tasks
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = RequestState.Loading
    )

    fun markTaskAsCompleted(task: ToDoTask): RequestState<Unit> {
        return repository.updateTask(task)
    }

    fun removeTask(taskId: String): RequestState<Unit> {
        return repository.removeTask(taskId)
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }
}