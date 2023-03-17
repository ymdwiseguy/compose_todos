package com.ymdwiseguy.todos.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ymdwiseguy.todos.ui.theme.TodosTheme
import com.ymdwiseguy.todos.viewmodel.StartViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun StartScreen(
    navigateToLoginScreen: () -> Unit,
    navigateToTodosScreen: () -> Unit,
) {
    val viewModel = koinViewModel<StartViewModel>()

    if (viewModel.credentialsAvailable) navigateToTodosScreen()
    else navigateToLoginScreen()
}

@Preview(widthDp = 360, heightDp = 640, showBackground = true)
@Composable
private fun StartScreenPreview() {
    TodosTheme {
        StartScreen(
            navigateToLoginScreen = {},
            navigateToTodosScreen = {}
        )
    }
}