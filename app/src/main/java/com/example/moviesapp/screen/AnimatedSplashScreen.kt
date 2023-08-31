package com.example.moviesapp.screen


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.myapplication.model.NavigationItem
import kotlinx.coroutines.delay
import com.example.moviesapp.R
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
@Composable
fun AnimatedSplashScreen(navController: NavController) {
    var showLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(3000)
        showLoading = false
        navController.popBackStack()
        navController.navigate(NavigationItem.Home.route)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
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
@Composable
fun LoadingAnimation(
    dotCount: Int = 3,
    circleSize: Dp = 25.dp,
    circleColor: Color = Color.Gray,
    spaceBetween: Dp = 10.dp,
    travelDistance: Dp = 20.dp,
    waveAmplitude: Dp = 30.dp,
    waveFrequency: Float = 8f,
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
                        color = circleColor,
                        shape = CircleShape
                    )
            )
        }
    }
}