package com.example.soundsense.ui.screen.microphone

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.soundsense.ui.theme.Green

@Composable
fun MicrophoneScreen() {
    MicrophoneContent()
}

@Composable
fun MicrophoneContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Green, Color.Transparent),
                    startY = Float.POSITIVE_INFINITY,
                    endY = 0f
                )
            )
            .padding(16.dp)
    ) {
        Column {
            Text(text = "ini page microphone")
        }
    }
}
