package com.ymdwiseguy.todos.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ymdwiseguy.todos.domain.Todo
import com.ymdwiseguy.todos.ui.theme.colors
import com.ymdwiseguy.todos.ui.theme.dimensions
import com.ymdwiseguy.todos.ui.theme.shapes

@Composable
fun TodoItem(
    it: Todo,
    removeTodo: (Todo) -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(dimensions.gapS, dimensions.gapXS)
            .background(color = colors.surfaceVariant, shape = shapes.small)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = it.name, modifier = Modifier
                .padding(dimensions.gapM)
        )
        IconButton(onClick = { removeTodo(it) }) {
            Icon(Icons.Outlined.Delete, contentDescription = "Delete ${it.name}")
        }
    }
}