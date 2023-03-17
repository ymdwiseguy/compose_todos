package com.ymdwiseguy.todos.repo

import com.ymdwiseguy.todos.domain.RemoteTodo
import com.ymdwiseguy.todos.domain.Todo
import retrofit2.HttpException

class TodosRepository(
    private val todosApi: TodosApi,
) {

    suspend fun getTodos(): List<Todo> {
        val response = todosApi.getTodos()
        return when{
            response.isSuccessful && response.body() != null -> {
                (response.body() as List<RemoteTodo>).map {
                    Todo(it.name)
                }
            }
            response.isSuccessful -> emptyList()
            else -> throw HttpException(response)
        }
    }

    suspend fun addTodo(todo: Todo){
        // TODO: implement me
    }
}
