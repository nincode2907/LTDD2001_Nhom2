package com.example.introapp

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.introapp.ui.theme.IntroAppTheme
import kotlinx.coroutines.delay

import kotlinx.coroutines.launch
import java.util.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            IntroAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    MainContent()
                }
            }
        }
    }

    @Composable
    fun MainContent() {
        var showLoading by remember { mutableStateOf(true) }
        var showHelloWorld by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            // Hiển thị loading dots trong 5 giây
            delay(5000)
            showLoading = false
            showHelloWorld = true
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (showHelloWorld) {
                Greeting("World")
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.logo),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(250.dp)
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    LoadingAnimation(
                        dotCount = 5,
                        circleSize = 20.dp,
                        circleColor = Color.Gray,
                        spaceBetween = 10.dp,
                        travelDistance = 20.dp,


                        )
                }
            }
        }
    }

    @Composable
    fun LoadingAnimation(
        dotCount: Int = 3,
        circleSize: Dp = 25.dp,
        circleColor: Color = Color.Gray,
        spaceBetween: Dp = 10.dp,
        travelDistance: Dp = 20.dp,
        waveAmplitude: Dp = 30.dp,
        waveFrequency: Float = 8f, // Số lần sóng trong 1 khoảng thời gian 1200ms
        modifier: Modifier = Modifier
    ) {

        val waveOffsets = remember { List(dotCount) { Animatable(0f) } }

        waveOffsets.forEachIndexed { index, animatable ->
            LaunchedEffect(key1 = animatable) {
                delay(index * 100L)
                animatable.animateTo(
                    targetValue = 1f,
                    animationSpec = infiniteRepeatable(
                        animation = keyframes {
                            durationMillis = 1200
                            0.0f at 0 with LinearOutSlowInEasing
                            1.0f at 600 with LinearOutSlowInEasing
                            0.0f at 1200 with LinearOutSlowInEasing
                        },
                        repeatMode = RepeatMode.Restart
                    )
                )
            }
        }

        val waveOffsetsValues = waveOffsets.map { it.value }
        val distance = with(LocalDensity.current) { travelDistance.toPx() }

        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(spaceBetween)
        ) {
            waveOffsetsValues.forEachIndexed { index, value ->

                Box(
                    modifier = Modifier
                        .size(circleSize)
                        .graphicsLayer {
                            translationY = waveOffsetsValues[index] * waveAmplitude.toPx()
                        }
                        .background(
                            color =circleColor ,
                            shape = CircleShape
                        )
                )
            }
        }
    }


    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            color = Color.White,
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun MainContentPreview() {
        IntroAppTheme {
            MainContent()
        }
    }
}