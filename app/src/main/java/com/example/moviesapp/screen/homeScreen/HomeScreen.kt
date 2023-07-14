package com.example.movieapp.screen.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun HomeScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Text(text = "HomeScreen", color = Color.White)
    }
}