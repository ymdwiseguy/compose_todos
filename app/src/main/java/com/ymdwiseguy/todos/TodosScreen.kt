package com.ymdwiseguy.todos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
    val viewData: List<Todo> = viewModel.viewData

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
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {

            item {
                Row {

                    Button(onClick = navigateToStartScreen) {
                        Text(text = "To Start Screen")
                    }
                    Button(onClick = navigateToLoginScreen) {
                        Text(text = "To Login Screen")
                    }
                }
            }

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