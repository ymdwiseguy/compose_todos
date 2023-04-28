package com.ymdwiseguy.todos.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.ymdwiseguy.todos.domain.Todo
import java.util.UUID

@Composable
fun TodosScreenFAB(addTodo: (Todo) -> Unit, viewData: List<Todo>) {
    FloatingActionButton(onClick = {
        addTodo(
            Todo(
                name = UUID.randomUUID().toString(),
                sortIndex = viewData.maxOf { it.sortIndex ?: 0 } + 1
            )
        )
    }) {
        Icon(Icons.TwoTone.Add, contentDescription = "new Todo")
    }
}