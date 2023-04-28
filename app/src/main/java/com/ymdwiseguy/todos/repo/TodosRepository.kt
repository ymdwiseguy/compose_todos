package com.ymdwiseguy.todos.repo

import com.ymdwiseguy.todos.domain.Todo
import kotlinx.coroutines.flow.map

class TodosRepository(
    private val todosApi: TodosApi,
    private val todosDataStore: TodosDataStore,
) {

    suspend fun addTodo(todo: Todo) = todosDataStore.write(todo)

    suspend fun removeTodo(todo: Todo) = todosDataStore.remove(todo)

    fun getTodos() = todosDataStore.todosFlow().map { it.sortedBy(Todo::sortIndex) }

    suspend fun clearAll() = todosDataStore.clear()

    // TODO: sync local data with remote data regularly / per socket?
    suspend fun syncTodos() {
        TODO()
//        val response = todosApi.getTodos()
//        return when{
//            response.isSuccessful && response.body() != null -> {
//                (response.body() as List<RemoteTodo>).map {
//                    Todo(it)
//                }
//            }
//            response.isSuccessful -> emptyList()
//            else -> throw HttpException(response)
//        }
//    }
//        val remoteTodo = RemoteTodo(todo)
//        val response = todosApi.storeTodos(remoteTodo)
//        if(!response.isSuccessful)
//            throw HttpException(response)
    }

}

