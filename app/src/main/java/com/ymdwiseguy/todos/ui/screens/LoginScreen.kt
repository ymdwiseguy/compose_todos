package com.ymdwiseguy.todos.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ymdwiseguy.todos.ui.theme.TodosTheme
import com.ymdwiseguy.todos.viewmodel.LoginViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    navigateToStartScreen: () -> Unit,
    navigateToTodosScreen: () -> Unit,
    viewModel: LoginViewModel = koinViewModel()
) {

    LaunchedEffect(viewModel.viewEvents?.eventId) {
        viewModel.viewEvents?.let {
            when (it) {
                is LoginViewModel.LoginViewEvents.Error -> navigateToStartScreen()
                is LoginViewModel.LoginViewEvents.NavigateToTodos -> navigateToTodosScreen()
            }
        }
    }

    LoginScreenContent(LoginScreenStateHolder(viewModel))
}

data class LoginScreenStateHolder(
    val username: String?,
    val password: String?,
    val updateUserName: (String) -> Unit,
    val updatePassword: (String) -> Unit,
    val login: () -> Unit,
) {
    constructor(loginViewModel: LoginViewModel) : this(
        username = loginViewModel.username,
        password = loginViewModel.password,
        updateUserName = loginViewModel::updateUserName,
        updatePassword = loginViewModel::updatePassword,
        login = loginViewModel::login,
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun LoginScreenContent(loginScreenStateHolder: LoginScreenStateHolder) {
    Scaffold(
        bottomBar = {
            Row(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.End) {
            Button(onClick = loginScreenStateHolder.login) {
                Text(text = "Login")
            }
            }
        }
    ) {
        Column(
            Modifier
                .padding(it)
                .padding(16.dp)) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Username", modifier = Modifier.width(120.dp))
                TextField(
                    value = loginScreenStateHolder.username ?: "",
                    onValueChange = loginScreenStateHolder.updateUserName
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Password", modifier = Modifier.width(120.dp))
                TextField(
                    value = loginScreenStateHolder.password ?: "",
                    onValueChange = loginScreenStateHolder.updatePassword
                )
            }
        }
    }

}

@Preview(widthDp = 360, heightDp = 400, showBackground = true)
@Composable
private fun LoginScreenPreview() {
    TodosTheme {
        LoginScreenContent(
            loginScreenStateHolder = LoginScreenStateHolder(
                username = "",
                password = "",
                updateUserName = {},
                updatePassword = {},
                login = {},
            ),
        )
    }
}
