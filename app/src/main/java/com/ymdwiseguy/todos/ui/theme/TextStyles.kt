package com.ymdwiseguy.todos.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private fun robotoTextStyle(
    fontSize: TextUnit,
    fontWeight: FontWeight,
    lineHeight: TextUnit,
    letterSpacing: TextUnit = TextUnit.Unspecified
) = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = fontSize,
    fontWeight = fontWeight,
    lineHeight = lineHeight,
    letterSpacing = letterSpacing
)

internal object Display {
    val large = robotoTextStyle(
        fontSize = 57.sp,
        fontWeight = W400,
        lineHeight = 64.sp
    )

    val medium = robotoTextStyle(
        fontSize = 45.sp,
        fontWeight = W500,
        lineHeight = 52.sp
    )

    val small = robotoTextStyle(
        fontSize = 36.sp,
        fontWeight = W400,
        lineHeight = 44.sp
    )
}

internal object Headline {
    val large = robotoTextStyle(
        fontSize = 32.sp,
        fontWeight = W400,
        lineHeight = 40.sp
    )

    val medium = robotoTextStyle(
        fontSize = 28.sp,
        fontWeight = W500,
        lineHeight = 36.sp
    )

    val small = robotoTextStyle(
        fontSize = 24.sp,
        fontWeight = W400,
        lineHeight = 32.sp
    )
}

internal object Title {
    val large = robotoTextStyle(
        fontSize = 22.sp,
        lineHeight = 28.sp,
        fontWeight = W400
    )
    val medium = robotoTextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = W500,
        letterSpacing = 0.15.sp
    )
    val small = robotoTextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = W500,
        letterSpacing = 0.1.sp
    )
}

internal object Label {
    val large = robotoTextStyle(
        fontSize = 14.sp,
        lineHeight = 28.sp,
        fontWeight = W500,
        letterSpacing = 0.1.sp,
    )
    val medium = robotoTextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = W500,
        letterSpacing = 0.5.sp
    )

    val small = robotoTextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = W500,
        letterSpacing = 0.5.sp
    )
}

internal object Body {
    val large = robotoTextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = W400,
        letterSpacing = 0.5.sp
    )
    val medium = robotoTextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = W400,
        letterSpacing = 0.25.sp
    )
    val small = robotoTextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = W400,
        letterSpacing = 0.4.sp
    )
}

@Composable
@Preview
private fun PreviewTextStyles() {
    TodosTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Display Large", style= typography.displayLarge)
                Text("Display Medium", style= typography.displayMedium)
                Text("Display Small", style= typography.displaySmall)
                Text("Headline Large", style= typography.headlineLarge)
                Text("Headline Medium", style= typography.headlineMedium)
                Text("Headline Small", style= typography.headlineSmall)
                Text("Title Large", style= typography.titleLarge)
                Text("Title Medium", style= typography.titleMedium)
                Text("Title Small", style= typography.titleSmall)
                Text("Body Large", style= typography.bodyLarge)
                Text("Body Medium", style= typography.bodyMedium)
                Text("Body Small", style= typography.bodySmall)
                Text("Label Large", style= typography.labelLarge)
                Text("Label Medium", style= typography.labelMedium)
                Text("Label Small", style= typography.labelSmall)
            }
        }
    }
}
