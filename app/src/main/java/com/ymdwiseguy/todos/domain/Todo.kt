package com.ymdwiseguy.todos.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Todo(
    @Json(name = "name") val name: String,
)

