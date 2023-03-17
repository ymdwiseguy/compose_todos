package com.ymdwiseguy.todos

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
        // TODO: implement login first
        viewModelScope.launch {
            runCatching {
                viewData = todosRepository.getTodos()
            }.onFailure {
                Log.e("TodosViewModel", it.message, it)
            }
        }
    }
}
