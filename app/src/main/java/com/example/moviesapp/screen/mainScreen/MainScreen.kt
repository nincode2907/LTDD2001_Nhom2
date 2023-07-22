package com.example.myapplication.screen.mainScreen

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.moviesapp.screen.AnimatedSplashScreen
import com.example.petadoption.bottomnav.BottomBarAnimationApp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val mainViewModel = MainViewModel()


    BottomBarAnimationApp(mainViewModel = mainViewModel)

}