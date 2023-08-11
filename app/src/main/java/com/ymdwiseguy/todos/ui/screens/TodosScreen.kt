package com.ymdwiseguy.todos.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ymdwiseguy.todos.domain.Todo
import com.ymdwiseguy.todos.ui.EditTodoDialog
import com.ymdwiseguy.todos.ui.theme.TodosTheme
import com.ymdwiseguy.todos.viewmodel.TodosViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TodosScreen(
    navigateToStartScreen: () -> Unit,
    navigateToLoginScreen: () -> Unit,
) {

    val viewModel = koinViewModel<TodosViewModel>()
    val viewData: List<Todo> by viewModel.viewData.collectAsStateWithLifecycle(emptyList())

    TodosScreenContent(
        navigateToStartScreen = navigateToStartScreen,
        navigateToLoginScreen = navigateToLoginScreen,
        addTodo = viewModel::addTodo,
        updateTodo = viewModel::updateTodo,
        removeTodo = viewModel::removeTodo,
        clearAll = viewModel::clearAll,
        showEditDialog = viewModel::showEditDialog,
        hideEditDialog = viewModel::hideEditDialog,
        viewData = viewData,
        editDialogVisible = viewModel.todoBeingEdited
    )
}

@Composable
private fun TodosScreenContent(
    navigateToStartScreen: () -> Unit,
    navigateToLoginScreen: () -> Unit,
    addTodo: (Todo) -> Unit,
    updateTodo: (String) -> Unit,
    removeTodo: (Todo) -> Unit,
    clearAll: () -> Unit,
    showEditDialog: (Todo) -> Unit,
    hideEditDialog: () -> Unit,
    viewData: List<Todo>,
    editDialogVisible: Todo?,
) {
    Scaffold(
        topBar = { TodosScreenTopAppBar(clearAll) },
        bottomBar = { TodosScreenBottomBar(navigateToStartScreen, navigateToLoginScreen) },
        floatingActionButton = { TodosScreenFAB(addTodo, viewData) }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            contentPadding = PaddingValues(bottom = 120.dp)
        ) {
            viewData.map {
                item { TodoItem(it, removeTodo, showEditDialog) }
            }
        }
    }

    editDialogVisible?.let {
        EditTodoDialog(save = updateTodo, close = hideEditDialog, it)
    }

}

@Preview
@Composable
private fun TodosScreenPreview() {

    val todos = remember {
        mutableStateListOf(
            Todo(name = "Sample Todo 1", sortIndex = 0),
            Todo(name = "Sample Todo 2", sortIndex = 0),
        )
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
    }

    fun removeTodo(todo: Todo) {
        todos.remove(todo)
    }

    fun clearAll() {
        todos.clear()
    }


    TodosTheme {
        TodosScreenContent(
            navigateToStartScreen = {},
            navigateToLoginScreen = {},
            addTodo = ::addTodo,
            updateTodo = {},
            removeTodo = ::removeTodo,
            clearAll = ::clearAll,
            showEditDialog = {},
            hideEditDialog = {},
            viewData = todos,
            editDialogVisible = null,
        )
    }
}