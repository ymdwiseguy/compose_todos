package com.ymdwiseguy.todos.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val gapXS: Dp = 4.dp,
    val gapS: Dp = 8.dp,
    val gapM: Dp = 12.dp,
    val gapL: Dp = 16.dp,
    val gapXL: Dp = 24.dp,
    val buttonHeight: Dp = 48.dp,
    val buttonHeightLarge: Dp = 56.dp,
)

@Composable
@Preview
private fun DimensionsPreview() {
    TodosTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.padding(dimensions.gapL)) {
                DimensionRow("Gap XS", dimensions.gapXS)
                DimensionRow("Gap S", dimensions.gapS)
                DimensionRow("Gap M", dimensions.gapM)
                DimensionRow("Gap L", dimensions.gapL)
                DimensionRow("Gap XL", dimensions.gapXL)
                DimensionRow("Button Height", dimensions.buttonHeight)
                DimensionRow("Button Height Large", dimensions.buttonHeightLarge)
            }
        }
    }
}

@Composable
private fun DimensionRow(text: String, size: Dp) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 16.dp, 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("$text (${size.value.toInt()} dp)")
        Box(
            modifier = Modifier
                .size(size, 16.dp)
                .clip(RectangleShape)
                .background(Color.Black)
        )
    }
}