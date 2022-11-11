package com.ymdwiseguy.todos.repo

import com.ymdwiseguy.todos.RemoteTodo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TodosApi {

    @GET("select.php")
    suspend fun getTodos() : Response<List<RemoteTodo>>

    @POST("insert.php")
    suspend fun storeTodos(@Body todo: RemoteTodo) : Response<RemoteTodo>
}