package com.ymdwiseguy.todos.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Suppress("unused")
internal abstract class Palette {
    val color0: Color = Color(0XFF000000)
    val color100: Color = Color(0XFFFFFFFF)
}

internal object PrimaryPalette : Palette() {
    val color10 = Color(0XFF001C39)
    val color40 = Color(0XFF0060AC)
    val color90 = Color(0XFFD2E4FF)
}

internal object SecondaryPalette : Palette() {
    val color10 = Color(0XFF111C2B)
    val color40 = Color(0XFF545F70)
    val color90 = Color(0XFFD7E3F8)
}

internal object NeutralPalette : Palette() {
    val color10 = Color(0XFF1B1B1B)
}

internal object NeutralVariantPalette : Palette() {
    val color30 = Color(0XFF43474E)
    val color50 = Color(0XFF73777F)
    val color80 = Color(0xFFC3C6CF)
    val color90 = Color(0XFFDFE2EB)
    val color95 = Color(0XFFEDF0F9)
}

internal object ErrorPalette : Palette() {
    val color10 = Color(0XFF410001)
    val color40 = Color(0XFFBA1B1B)
    val color90 = Color(0XFFFFDAD4)
}


@Composable
@Preview
private fun PreviewColors() {
    TodosTheme {

        Surface(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.padding(dimensions.gapL, 0.dp)) {
                ColorTitleRow("Material Default:")
                ColorRow("Primary", MaterialTheme.colorScheme.primary)
                ColorRow("Secondary", MaterialTheme.colorScheme.secondary)
                ColorRow("Background", MaterialTheme.colorScheme.background)
                ColorRow("Surface", MaterialTheme.colorScheme.surface)
                ColorRow("on Primary", MaterialTheme.colorScheme.onPrimary)
                ColorRow("on Secondary", MaterialTheme.colorScheme.onSecondary)
                ColorRow("on Background", MaterialTheme.colorScheme.onBackground)
                ColorRow("on Surface", MaterialTheme.colorScheme.onSurface)
                ColorRow("on Error", MaterialTheme.colorScheme.onError)

                ColorTitleRow("Material 3 Default:")
                ColorRow("Secondary Container", colors.secondaryContainer)
                ColorRow("on Secondary Container", colors.onSecondaryContainer)
                ColorRow("Outline Color", colors.outline)
                ColorRow("Error", colors.error)
                ColorRow("Error Container", colors.errorContainer)
                ColorRow("on Error Container", colors.onErrorContainer)

            }
        }
    }
}

@Composable
private fun ColorTitleRow(text: String) =
    Row(Modifier.padding(0.dp, dimensions.gapM)) {
        Text(text)
    }

@Composable
private fun ColorRow(text: String, hexValue: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 16.dp, 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text)
        Box(
            modifier = Modifier
                .size(16.dp)
                .clip(CircleShape)
                .background(hexValue)
                .border(1.dp, Color.Black, CircleShape)
        )
    }
}