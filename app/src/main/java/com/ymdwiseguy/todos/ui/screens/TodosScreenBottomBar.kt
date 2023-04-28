package com.ymdwiseguy.todos.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ymdwiseguy.todos.ui.theme.colors
import com.ymdwiseguy.todos.ui.theme.dimensions

@Composable
fun TodosScreenBottomBar(
    navigateToStartScreen: () -> Unit,
    navigateToLoginScreen: () -> Unit
) {
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
}