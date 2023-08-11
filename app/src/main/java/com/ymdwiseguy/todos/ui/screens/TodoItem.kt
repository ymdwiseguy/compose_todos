package com.ymdwiseguy.todos.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ymdwiseguy.todos.domain.Todo
import com.ymdwiseguy.todos.ui.theme.colors
import com.ymdwiseguy.todos.ui.theme.dimensions
import com.ymdwiseguy.todos.ui.theme.shapes

@Composable
fun TodoItem(
    todo: Todo,
    removeTodo: (Todo) -> Unit,
    showEditDialog: (Todo) -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(horizontal = dimensions.gapS, vertical = dimensions.gapXS)
            .background(color = colors.surfaceVariant, shape = shapes.small)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${todo.sortIndex} ::",
            modifier = Modifier
                .padding(start = dimensions.gapM),
        )
        Text(
            text = todo.name,
            modifier = Modifier
                .weight(1f)
                .padding(start = dimensions.gapM, top = dimensions.gapS, bottom = dimensions.gapS)
                .clickable { showEditDialog(todo) },
        )
        IconButton(
            onClick = { removeTodo(todo) },
            modifier = Modifier.size(56.dp)
        ) {
            Icon(Icons.Outlined.Delete, contentDescription = "Delete ${todo.name}")
        }
    }
}

@Preview
@Composable
private fun TodoItemPreview() {
    Column {
        TodoItem(todo = Todo(name = "one"), removeTodo = {}, showEditDialog = {})
        TodoItem(todo = Todo(name = "2"), removeTodo = {}, showEditDialog = {})
        TodoItem(todo = Todo(name = "yet another todo"), removeTodo = {}, showEditDialog = {})
        TodoItem(todo = Todo(name = "todo with linebreak\nthis is line two"), removeTodo = {}, showEditDialog = {})
        TodoItem(todo = Todo(name = "a very long text for this todo, lets see some overflow"), removeTodo = {}, showEditDialog = {})
    }
}