package com.ymdwiseguy.todos.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ymdwiseguy.todos.domain.Todo
import com.ymdwiseguy.todos.repo.TodosRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodosViewModel(
    private val todosRepository: TodosRepository
) : ViewModel() {

    val viewData: StateFlow<List<Todo>> = todosRepository
        .getTodos()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    // TODO: add error view state and redirect to login when not logged in

    fun addTodo(todo: Todo) {
        viewModelScope.launch {
            runCatching {
                todosRepository.addTodo(todo)
            }.onFailure {
                Log.e("TodosViewModel", it.message, it)
            }
        }
    }

    fun removeTodo(todo: Todo) {
        viewModelScope.launch {
            runCatching {
                todosRepository.removeTodo(todo)
            }.onFailure {
                Log.e("TodosViewModel", it.message, it)
            }
        }
    }

    fun clearAll() {
        viewModelScope.launch {
            runCatching {
                todosRepository.clearAll()
            }.onFailure {
                Log.e("TodosViewModel", it.message, it)
            }
        }
    }
}
