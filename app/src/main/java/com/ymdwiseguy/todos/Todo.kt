package com.ymdwiseguy.todos

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Todo(
    @Json(name = "name") val name: String,
)

@JsonClass(generateAdapter = true)
data class RemoteTodo(
    @Json(name = "uuid") val uuid: String,
    @Json(name = "name") val name: String,
    @Json(name = "checked") val checked: String,
    @Json(name = "deleted") val deleted: String,
    @Json(name = "modified") val modified: String,
    @Json(name = "group_name") val groupName: String,
)