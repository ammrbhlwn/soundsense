package com.example.soundsense.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soundsense.ui.components.LoaderAnimation
import com.example.soundsense.ui.theme.Green
import com.example.soundsense.ui.theme.SoundSenseTheme

@Composable
fun HomeScreen(
    navigateToSpeech: () -> Unit,
) {
    HomeContent(
        navigateToSpeech = navigateToSpeech,
    )
}

@Composable
fun HomeContent(
    navigateToSpeech: () -> Unit,
) {
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
            .padding(16.dp),

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement =Arrangement.Center
        ) {
            LoaderAnimation()
            Button(
                onClick = navigateToSpeech,
                modifier = Modifier
                    .height(48.dp),
                shape = RoundedCornerShape(8.dp),
                colors  = ButtonDefaults.buttonColors(
                    containerColor        = Color(0xFF2F88FF),
                    contentColor          = Color.White,
                )
            ) {
                Text(
                    "Mulai Berbicara",
                    fontSize = 16.sp,
                    color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    SoundSenseTheme {
        HomeContent(
            navigateToSpeech = { }
        )
    }
}