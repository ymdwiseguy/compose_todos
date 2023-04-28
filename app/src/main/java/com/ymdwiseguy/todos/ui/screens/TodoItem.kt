package com.ymdwiseguy.todos.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
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
            .padding(horizontal = dimensions.gapS, vertical = dimensions.gapXS)
            .background(color = colors.surfaceVariant, shape = shapes.small)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${it.sortIndex} :: ${it.name}",
            modifier = Modifier
                .weight(1f)
                .padding(start = dimensions.gapM),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
        IconButton(
            onClick = { removeTodo(it) },
            modifier = Modifier.size(56.dp)
        ) {
            Icon(Icons.Outlined.Delete, contentDescription = "Delete ${it.name}")
        }
    }
}