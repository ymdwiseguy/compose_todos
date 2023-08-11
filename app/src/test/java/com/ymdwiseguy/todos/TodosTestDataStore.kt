package com.ymdwiseguy.todos

import com.ymdwiseguy.todos.domain.Todo
import com.ymdwiseguy.todos.repo.TodosDataStoreInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class TodosTestDataStore : TodosDataStoreInterface {

    private val datastore = MutableStateFlow<Map<String, Todo>>(emptyMap())

    override fun todosFlow(): Flow<List<Todo>> = datastore
        .asStateFlow()
        .map { it.values.toList() }

    override suspend fun write(todo: Todo) {
        datastore.update {
            it.toMutableMap().apply {
                put(todo.uuid, todo)
            }
        }
    }

    override suspend fun remove(todo: Todo) {
        datastore.update {
            val updatedMap: MutableMap<String, Todo> = it.toMutableMap()
            updatedMap.remove(key = todo.uuid)
            return@update updatedMap
        }
    }

    override suspend fun clear() {
        datastore.update { emptyMap() }
    }

}