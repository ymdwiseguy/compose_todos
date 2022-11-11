package com.ymdwiseguy.todos

import com.ymdwiseguy.todos.repo.TodosApi
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
}
