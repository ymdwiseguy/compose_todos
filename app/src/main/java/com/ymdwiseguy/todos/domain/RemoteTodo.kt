package com.ymdwiseguy.todos.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteTodo(
    @Json(name = "uuid") val uuid: String,
    @Json(name = "name") val name: String,
    @Json(name = "sort_index") val sortIndex: Int? = null,
    @Json(name = "checked") val checked: String,
    @Json(name = "deleted") val deleted: String,
    @Json(name = "modified") val modified: String,
    @Json(name = "group_name") val groupName: String,
){
    constructor(todo: Todo) : this(
        uuid = todo.uuid,
        name = todo.name,
        sortIndex = todo.sortIndex,
        checked = todo.checked.toString(),
        deleted = todo.deleted.toString(),
        modified = todo.modified.toString(),
        groupName = todo.groupName,
    )
}