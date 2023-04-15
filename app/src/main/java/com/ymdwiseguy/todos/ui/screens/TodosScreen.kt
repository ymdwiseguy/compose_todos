package com.ymdwiseguy.todos.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ymdwiseguy.todos.R
import com.ymdwiseguy.todos.viewmodel.TodosViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ymdwiseguy.todos.domain.Todo
import com.ymdwiseguy.todos.ui.theme.colors
import com.ymdwiseguy.todos.ui.theme.dimensions
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodosScreen(
    navigateToStartScreen: () -> Unit,
    navigateToLoginScreen: () -> Unit,
) {

    val viewModel = koinViewModel<TodosViewModel>()
    val viewData: List<Todo> by viewModel.viewData.collectAsStateWithLifecycle(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                navigationIcon = {},
                actions = {},
                scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
            )
        },
        bottomBar = {
            Divider()
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .background(colors.surfaceVariant)
                    .padding(dimensions.gapL, dimensions.gapS)
                    .fillMaxWidth()
            ) {
                Button(onClick = navigateToStartScreen) {
                    Text(text = "To Start Screen")
                }
                Button(onClick = navigateToLoginScreen) {
                    Text(text = "To Login Screen")
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.addTodo(
                Todo(name = "Random Todo")
            ) }) {
                Icon(Icons.TwoTone.Add, contentDescription = "new Todo")
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {

            viewData.map {
                item {

                    Text(
                        text = it.name, modifier = Modifier
                            .background(color = colors.primary)
                            .padding(dimensions.gapL)
                    )
                }
            }

        }
    }
}