package com.example.movieapp.screen.rankingScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.myapplication.screen.mainScreen.MainViewModel
import com.example.petadoption.bottomnav.BottomBar
import kotlinx.coroutines.launch

@Composable
fun RankingScreen(
    mainViewModel: MainViewModel,
    navController: NavController,
    bottomBarState: MutableState<Boolean>
) {
    Scaffold(
        bottomBar = {
            BottomBar(
                mainViewModel = mainViewModel,
                navController = navController,
                bottomBarState = bottomBarState
            )
        },
    ) { paddingValues ->

        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .background(Color.Black)
        ) {
            Text(text = "RankingScreen", color = Color.White)
        }
    }
}