package com.ymdwiseguy.todos

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
}
