package com.ymdwiseguy.todos.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.dp

val dimensions @Composable get() = LocalDimensions.current
val colors @Composable get() = MaterialTheme.colorScheme
val typography @Composable get() = MaterialTheme.typography

val LocalDimensions = compositionLocalOf { Dimensions() }


@Composable
fun TodosTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    val material3Typography = Typography(
        displayLarge = Display.large,
        displayMedium = Display.medium,
        displaySmall = Display.small,
        headlineLarge = Headline.large,
        headlineMedium = Headline.medium,
        headlineSmall = Headline.small,
        titleLarge = Title.large,
        titleMedium = Title.medium,
        titleSmall = Title.small,
        bodyLarge = Body.large,
        bodyMedium = Body.medium,
        bodySmall = Body.small,
        labelLarge = Label.large,
        labelMedium = Label.medium,
        labelSmall = Label.small
    )

    val colorScheme = lightColorScheme(
        primary = PrimaryPalette.color40,
        onPrimary = PrimaryPalette.color100,
        primaryContainer = PrimaryPalette.color90,
        onPrimaryContainer = PrimaryPalette.color10,
        secondary = SecondaryPalette.color40,
        onSecondary = SecondaryPalette.color100,
        secondaryContainer = SecondaryPalette.color90,
        onSecondaryContainer = SecondaryPalette.color10,
        background = NeutralVariantPalette.color95,
        onBackground = NeutralPalette.color10,
        surface = NeutralPalette.color100,
        onSurface = NeutralPalette.color10,
        surfaceVariant = NeutralVariantPalette.color90,
        onSurfaceVariant = NeutralVariantPalette.color30,
        error = ErrorPalette.color40,
        onError = ErrorPalette.color100,
        errorContainer = ErrorPalette.color90,
        onErrorContainer = ErrorPalette.color10,
        outline = NeutralVariantPalette.color50,
        surfaceTint = NeutralPalette.color100,
    )
    CompositionLocalProvider(
        LocalDimensions provides Dimensions(),
    ){

    MaterialTheme(
        content = content,
        colorScheme = colorScheme,
        shapes = Shapes(
            medium = RoundedCornerShape(8.dp),
            large = RoundedCornerShape(28.dp)
        ),
        typography = material3Typography
    )
    }
}