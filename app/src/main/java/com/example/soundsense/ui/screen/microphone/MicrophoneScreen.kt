package com.example.soundsense.ui.screen.microphone

import android.R.attr.name
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soundsense.R
import com.example.soundsense.ui.theme.Green
import com.example.soundsense.ui.theme.SoundSenseTheme

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
            .padding(24.dp, 48.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Hello Clara, open your book page 181",
                fontSize = 28.sp,
                lineHeight = 40.sp
            )
            Box(
                modifier = Modifier
                    .background(Color(0xFFE0EDFF), shape = CircleShape)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .background(Color(0xFFABCFFF), shape = CircleShape)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .background(Color(0xFF2F88FF), shape = CircleShape)
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_speak),
                            contentDescription = R.string.ic_speak.toString(),
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.size(100.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailImagePreview() {
    SoundSenseTheme {
        MicrophoneScreen()
    }
}