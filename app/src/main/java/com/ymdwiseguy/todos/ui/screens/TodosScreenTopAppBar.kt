package com.ymdwiseguy.todos.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ymdwiseguy.todos.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TodosScreenTopAppBar(clearAll: () -> Unit) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        navigationIcon = {},
        actions = {
            IconButton(onClick = { clearAll() }) {
                Icon(Icons.Outlined.Delete, contentDescription = "Delete ALL Todos")
            }
        },
        scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    )
}