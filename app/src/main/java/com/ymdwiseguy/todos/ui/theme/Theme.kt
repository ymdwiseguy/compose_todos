package com.ymdwiseguy.todos.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalContext

val dimensions @Composable get() = LocalDimensions.current
val colors @Composable get() = MaterialTheme.colorScheme
val typography @Composable get() = MaterialTheme.typography
val shapes @Composable get() = MaterialTheme.shapes

val LocalDimensions = compositionLocalOf { Dimensions() }

@Composable
fun TodosTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    CompositionLocalProvider(
        LocalDimensions provides Dimensions(),
    ) {

        MaterialTheme(
            content = content,
            colorScheme = if (darkTheme) dynamicDarkColorScheme(LocalContext.current) else dynamicLightColorScheme(
                LocalContext.current
            ),
        )
    }
}