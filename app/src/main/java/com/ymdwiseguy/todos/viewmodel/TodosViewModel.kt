package com.ymdwiseguy.todos.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ymdwiseguy.todos.domain.Todo
import com.ymdwiseguy.todos.repo.TodosRepository
import kotlinx.coroutines.launch

class TodosViewModel(
    private val todosRepository: TodosRepository
) : ViewModel() {

    var viewData by mutableStateOf<List<Todo>>(emptyList())
        private set

    fun refresh() {
        viewModelScope.launch {
            runCatching {
                viewData = todosRepository.getTodos()
            }.onFailure {
                Log.e("TodosViewModel", it.message, it)
            }
        }
    }

    fun addTodo(todo: Todo){
        viewModelScope.launch {
            runCatching {
                todosRepository.addTodo(todo)
            }.onFailure {
                // TODO: add error view state and redirect to login
                Log.e("TodosViewModel", it.message, it)
            }
        }
    }
}
