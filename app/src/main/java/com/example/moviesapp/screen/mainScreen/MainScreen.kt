package com.example.myapplication.screen.mainScreen

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import com.example.petadoption.bottomnav.BottomBarAnimationApp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val mainViewModel = MainViewModel()
    BottomBarAnimationApp(mainViewModel = mainViewModel)

}