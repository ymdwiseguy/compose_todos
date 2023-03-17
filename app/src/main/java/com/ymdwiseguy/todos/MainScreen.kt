package com.ymdwiseguy.todos

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ymdwiseguy.todos.ui.screens.LoginScreen
import com.ymdwiseguy.todos.ui.screens.StartScreen
import com.ymdwiseguy.todos.ui.screens.TodosScreen
import com.ymdwiseguy.todos.ui.theme.TodosTheme
import dev.olshevski.navigation.reimagined.NavBackHandler
import dev.olshevski.navigation.reimagined.NavController
import dev.olshevski.navigation.reimagined.NavHost
import dev.olshevski.navigation.reimagined.navigate
import dev.olshevski.navigation.reimagined.rememberNavController

@Composable
fun MainScreen() {

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

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    TodosTheme {
        MainScreen()
    }
}