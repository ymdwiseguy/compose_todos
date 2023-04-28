package com.ymdwiseguy.todos.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.OffsetDateTime
import java.util.*

@JsonClass(generateAdapter = true)
data class Todo(
    @Json(name = "uuid") val uuid: String = UUID.randomUUID().toString(),
    @Json(name = "name") val name: String,
    @Json(name = "sort_index") val sortIndex: Int,
    @Json(name = "checked") val checked: Boolean = false,
    @Json(name = "deleted") val deleted: Boolean = false,
    @Json(name = "modified") val modified: OffsetDateTime = OffsetDateTime.now(),
    @Json(name = "group_name") val groupName: String = "",
) {
    constructor(remoteTodo: RemoteTodo) : this(
        uuid = remoteTodo.uuid,
        name = remoteTodo.name,
        sortIndex = remoteTodo.sortIndex,
        checked = remoteTodo.checked.toBoolean(),
        deleted = remoteTodo.deleted.toBoolean(),
        modified = OffsetDateTime.parse(remoteTodo.modified),
        groupName = remoteTodo.groupName
    )
}

