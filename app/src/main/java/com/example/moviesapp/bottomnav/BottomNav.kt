package com.example.petadoption.bottomnav

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import codes.andreirozov.bottombaranimation.ui.theme.BottomBarAnimationTheme
import com.example.movieapp.screen.homeScreen.HomeScreen
import com.example.movieapp.screen.rankingScreen.RankingScreen
import com.example.movieapp.screen.searchScreen.SearchScreen
import com.example.moviesapp.screen.AnimatedSplashScreen
import com.example.moviesapp.screen.categoryMoviesCreen.CategoryMoviesScreen
import com.example.moviesapp.screen.comingSoonScreen.ComingSoonScreen
import com.example.myapplication.screen.mainScreen.MainViewModel
import com.example.myapplication.model.NavigationItem

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun BottomBar(
    mainViewModel: MainViewModel,
    navController: NavController,
    bottomBarState: MutableState<Boolean>
) {
    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntry?.destination
    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            BottomNavigation(backgroundColor = Color(0xFF1F1F1D)) {
                mainViewModel.uiState.value.navigationItems.forEach { item ->
                    AddItem(
                        screen = item,
                        currentDestination = currentDestination,
                        navController = navController
                    )
                }

            }
        }
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BottomBarAnimationApp(mainViewModel: MainViewModel) {

    // State of bottomBar, set state to false, if current page route is "car_details"
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

    BottomBarAnimationTheme {
        val navController = rememberNavController()

        // Subscribe to navBackStackEntry, required to get current route
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        // Control TopBar and BottomBar
        when (navBackStackEntry?.destination?.route) {
            "Splash" -> {
                // Hide BottomBar
                bottomBarState.value = false
            }

            "Home" -> {
                // Show BottomBar
                bottomBarState.value = true
            }

            "Ranking" -> {
                // Show BottomBar
                bottomBarState.value = true
            }

            "Search" -> {
                // Show BottomBar
                bottomBarState.value = true
            }

            "Calendar" -> {
                // Show BottomBar
                bottomBarState.value = true
            }

            "PlayVideo" -> {
                // Hide BottomBar
                bottomBarState.value = false

            }
        }

        NavHost(
            modifier = Modifier.padding(),
            navController = navController,
            startDestination = "Splash",
        ) {
            composable(NavigationItem.Home.route) {
                HomeScreen(
                    mainViewModel = mainViewModel,
                    navController = navController,
                    bottomBarState = bottomBarState
                )
            }
            composable(NavigationItem.Ranking.route) {
                RankingScreen(
                    mainViewModel = mainViewModel,
                    navController = navController,
                    bottomBarState = bottomBarState
                )
            }
            composable(NavigationItem.Search.route) {
                SearchScreen(
                    mainViewModel = mainViewModel,
                    navController = navController,
                    bottomBarState = bottomBarState
                )
            }
            composable(NavigationItem.ComingSoon.route) {
                ComingSoonScreen(
                    mainViewModel = mainViewModel,
                    navController = navController,
                    bottomBarState = bottomBarState
                )
            }

            composable(NavigationItem.PlayVideo.route) {
            }
            composable("CategoryMovies/{nameCate}") { backStackEntry ->
                CategoryMoviesScreen(
                    backStackEntry.arguments?.getString("nameCate")!!,
                    navController = navController
                )
            }
            composable(route = "Splash") {
                AnimatedSplashScreen(navController = navController)
            }
        }
    }
}


@Composable
fun RowScope.AddItem(
    screen: NavigationItem,
    currentDestination: NavDestination?,
    navController: NavController
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
    val background =
        if (selected) Color.Transparent else Color.Transparent
    val contentColor =
        if (selected) Color.White else Color.White


    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(background)
            .clickable(onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }

            })
    ) {
        Row(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)

        ) {
            Icon(

                painter = painterResource(id = if (selected) screen.image else screen.imageSelected),
                contentDescription = "icon",
                tint = contentColor
            )
            AnimatedVisibility(visible = selected) {
                Text(
                    text = screen.title,
                    color = contentColor
                )
            }
        }
    }
}

