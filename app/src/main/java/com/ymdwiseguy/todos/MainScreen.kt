package com.ymdwiseguy.todos

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ymdwiseguy.todos.ui.theme.TodosTheme
import dev.olshevski.navigation.reimagined.NavBackHandler
import dev.olshevski.navigation.reimagined.NavController
import dev.olshevski.navigation.reimagined.NavHost
import dev.olshevski.navigation.reimagined.navigate
import dev.olshevski.navigation.reimagined.rememberNavController

@Composable
fun MainScreen() {

    // Implementation with "compose-navigation-reimagined"
    val navController = rememberNavController<Destination>(
        startDestination = Destination.Start
    )

    NavBackHandler(controller = navController)

    NavHost(navController) { destination ->
        when (destination) {
            Destination.Start -> {
                ResetNavigationBackstack(navController)
                StartScreen(
                    navigateToLoginScreen = { navController.navigate(Destination.Login) },
                    navigateToTodosScreen = { navController.navigate(Destination.Todos) },
                )
            }
            Destination.Login -> LoginScreen(
                navigateToStartScreen = { navController.navigate(Destination.Start) },
                navigateToTodosScreen = { navController.navigate(Destination.Todos) },
            )
            Destination.Todos -> TodosScreen(
                navigateToStartScreen = { navController.navigate(Destination.Start) },
                navigateToLoginScreen = { navController.navigate(Destination.Login) },
            )
        }
    }

}

@Composable
private fun ResetNavigationBackstack(navController: NavController<Destination>) {
    navController.setNewBackstack(
        listOf(navController.backstack.entries.first())
    )
}

@Composable
fun LoginScreen(
    navigateToStartScreen: () -> Unit,
    navigateToTodosScreen: () -> Unit
) {
    Text(text = "Login Screen")
    Button(onClick = navigateToStartScreen) {
        Text(text = "To Start Screen")
    }
    Button(onClick = navigateToTodosScreen) {
        Text(text = "To Todos Screen")
    }
}

@Composable
fun StartScreen(
    navigateToLoginScreen: () -> Unit,
    navigateToTodosScreen: () -> Unit
) {
    Text(text = "Start Screen")
    Button(onClick = navigateToLoginScreen) {
        Text(text = "To Login Screen")
    }
    Button(onClick = navigateToTodosScreen) {
        Text(text = "To Todos Screen")
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    TodosTheme {
        MainScreen()
    }
}