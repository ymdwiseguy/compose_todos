package com.ymdwiseguy.todos.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.ymdwiseguy.todos.domain.Todo
import com.ymdwiseguy.todos.ui.theme.TodosTheme
import com.ymdwiseguy.todos.ui.theme.dimensions
import com.ymdwiseguy.todos.ui.theme.shapes

@Composable
fun EditTodoDialog(
    save: (String) -> Unit,
    close: () -> Unit,
    todo: Todo = Todo()
) {

    val textFieldValue = remember(todo) {
        mutableStateOf(todo.name)
    }

    Dialog(onDismissRequest = close) {
        ElevatedCard(
            modifier = Modifier.fillMaxWidth(), shape = shapes.extraLarge
        ) {
            Column {
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = dimensions.gapM)
                ) {
                    IconButton(onClick = close) {
                        Icon(imageVector = Icons.Default.Close, "close")
                    }
                }
                OutlinedTextField(
                    value = textFieldValue.value,
                    onValueChange = {
                        textFieldValue.value = it
                        save(it)
                    },
                    modifier = Modifier.padding(
                        bottom = dimensions.gapXL,
                        start = dimensions.gapXL,
                        end = dimensions.gapXL
                    )
                )
            }
        }
    }
}

@Preview(widthDp = 360)
@Composable
private fun EditTodoDialogPreview() {
    var todo: Todo by remember {
        mutableStateOf(Todo())
    }

    fun updateText(newValue: String) {
        todo = todo.copy(
            name = newValue
        )
    }

    TodosTheme() {
        Column {
            EditTodoDialog(
                save = ::updateText,
                close = {},
                todo = todo,
            )
        }
    }
}