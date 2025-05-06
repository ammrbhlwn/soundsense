package com.example.soundsense.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.soundsense.ui.theme.Green
import com.example.soundsense.ui.theme.SoundSenseTheme

@Composable
fun HomeScreen() {
    HomeContent()
}

@Composable
fun HomeContent() {
    val greenMint = Green

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(greenMint, Color.White, greenMint),
                    start = Offset(-750f, -750f),
                    end = Offset(2500f, 2500f)
                )
            )
            .padding(16.dp)
    ) {
        Column {
            Text(text = "ini page Homepage")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    SoundSenseTheme {
        HomeContent()
    }
}