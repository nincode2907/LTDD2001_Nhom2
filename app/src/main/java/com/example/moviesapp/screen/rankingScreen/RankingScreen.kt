package com.example.movieapp.screen.rankingScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun RankingScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Black)) {
        Text(text = "RankingScreen", color = Color.White)
    }
}