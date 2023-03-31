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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.AutofillNode
import androidx.compose.ui.autofill.AutofillType
import androidx.compose.ui.composed
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalAutofill
import androidx.compose.ui.platform.LocalAutofillTree
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ymdwiseguy.todos.ui.theme.TodosTheme
import com.ymdwiseguy.todos.ui.theme.dimensions
import com.ymdwiseguy.todos.ui.theme.typography
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
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
private fun LoginScreenContent(loginScreenStateHolder: LoginScreenStateHolder) {
    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensions.gapL), horizontalArrangement = Arrangement.End
            ) {
                Button(onClick = loginScreenStateHolder.login) {
                    Text(text = "Login")
                }
            }
        }
    ) {
        Column(
            Modifier
                .padding(it)
                .padding(dimensions.gapL)
        ) {
            Text(
                text = "Login",
                style = typography.headlineLarge,
                modifier = Modifier.padding(bottom = dimensions.gapL)
            )

            AutofillRow(
                value = loginScreenStateHolder.username,
                updateValue = loginScreenStateHolder.updateUserName,
                label = "Username",
                autofillType = AutofillType.Username,
                modifier = Modifier
                    .padding(bottom = 8.dp)
            )

            AutofillRow(
                value = loginScreenStateHolder.password,
                updateValue = loginScreenStateHolder.updatePassword,
                label = "Password",
                autofillType = AutofillType.Password,
                modifier = Modifier
                    .padding(bottom = 120.dp)
            )

            Text(
                text = "Mia war hier",
                style = MaterialTheme.typography.displayLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
            Text(
                text = "🐼",
                style = MaterialTheme.typography.displayLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }

}

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
private fun AutofillRow(
    value: String?,
    updateValue: (String) -> Unit,
    label: String,
    autofillType: AutofillType,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, modifier = Modifier.width(120.dp))
        TextField(
            value = value ?: "",
            onValueChange = updateValue,
            modifier = Modifier.autofill(
                autofillTypes = listOf(autofillType),
                onFill = updateValue
            )
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.autofill(
    autofillTypes: List<AutofillType>,
    onFill: ((String) -> Unit),
) = composed {
    val autofill = LocalAutofill.current
    val autofillNode = AutofillNode(onFill = onFill, autofillTypes = autofillTypes)
    LocalAutofillTree.current += autofillNode

    this.onGloballyPositioned {
        autofillNode.boundingBox = it.boundsInWindow()
    }.onFocusChanged { focusState ->
        autofill?.run {
            if (focusState.isFocused) {
                requestAutofillForNode(autofillNode)
            } else {
                cancelAutofillForNode(autofillNode)
            }
        }
    }
}

@Preview(widthDp = 360, heightDp = 680, showBackground = true)
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
