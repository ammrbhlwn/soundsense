package com.example.soundsense.ui.screen.microphone

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.soundsense.AppAction
import com.example.soundsense.AppViewModel
import com.example.soundsense.R
import com.example.soundsense.RealSpeechToText
import com.example.soundsense.ui.theme.Green
import com.example.soundsense.ui.theme.SoundSenseTheme

@Composable
fun MicrophoneScreen(
    navigateBack: () -> Unit,
) {
    MicrophoneContent(
        onBackClick = navigateBack,
    )
}

@Composable
fun MicrophoneContent(
    onBackClick: () -> Unit,
) {
    val context = LocalContext.current
    var permission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.RECORD_AUDIO
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        permission = granted
    }

    LaunchedEffect(permission) {
        if (!permission) {
            launcher.launch(Manifest.permission.RECORD_AUDIO)
        }
    }

    val viewModel = viewModel {
        val app = get(APPLICATION_KEY)!!
        val stt = RealSpeechToText(app.applicationContext)
        AppViewModel(stt)
    }

    var isRecording by remember { mutableStateOf(false) }
    var pressed by remember { mutableStateOf(false) }

    val buttonScale by animateFloatAsState(
        targetValue = if (pressed) 0.8f else 1f,
        animationSpec = spring(
            stiffness = Spring.StiffnessLow,
            dampingRatio = Spring.DampingRatioMediumBouncy,
            visibilityThreshold = 0.001f
        ),
        label = "Button Scale"
    )

    if (permission) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Green, Color.White),
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
                Column {
                    Box(
                        modifier = Modifier
                            .background(LightGray, RoundedCornerShape(8.dp))
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back),
                            tint = Color.Black,
                            modifier = Modifier
                                .size(40.dp)
                                .padding(4.dp)
                                .clickable { onBackClick() }
                        )
                    }

                    Spacer(modifier = Modifier.height(80.dp))

                    Text(
                        text = if (isRecording) "Merekam..." else "Tahan untuk memulai merekam",
                        fontSize = 20.sp,
                        color = if (isRecording) Color.Red else Color.Gray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = viewModel.state.display.ifEmpty { "Belum ada input suara..." },
                        color = Color.Black,
                        fontSize = 28.sp,
                        lineHeight = 40.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Box(
                    modifier = Modifier
                        .background(Color(0xFFE0EDFF), shape = CircleShape)
                        .padding(16.dp)
                        .scale(buttonScale)
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onPress = {
                                    pressed = true
                                    isRecording = true
                                    viewModel.send(AppAction.StartRecord)
                                    tryAwaitRelease()
                                    pressed = false
                                    isRecording = false
                                    viewModel.send(AppAction.EndRecord)
                                }
                            )
                        },
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
                                .background(
                                    if (isRecording) Color.Red else Color(0xFF2F88FF),
                                    shape = CircleShape
                                )
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
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(16.dp))
                Text("Meminta izin akses microphone...")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailImagePreview() {
    SoundSenseTheme {
        MicrophoneScreen(
            navigateBack = {},
        )
    }
}