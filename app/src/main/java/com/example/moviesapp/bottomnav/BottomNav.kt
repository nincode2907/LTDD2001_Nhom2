package com.example.petadoption.bottomnav

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import codes.andreirozov.bottombaranimation.ui.theme.BottomBarAnimationTheme
import com.example.movieapp.screen.homeScreen.HomeScreen
import com.example.movieapp.screen.rankingScreen.RankingScreen
import com.example.movieapp.screen.searchScreen.SearchScreen
import com.example.moviesapp.model.CategoryMovieBookNavigation
import com.example.moviesapp.model.CategoryMovieNavType
import com.example.moviesapp.model.Movie
import com.example.moviesapp.model.MovieBookNavigation
import com.example.moviesapp.model.MoviesNavType
import com.example.moviesapp.presentation.favourite.FavouriteList
import com.example.moviesapp.presentation.favourite.FavouriteMoviesModel
import com.example.moviesapp.presentation.signIn.GoogleAuthUiClient
import com.example.moviesapp.presentation.signIn.SignInScreen
import com.example.moviesapp.presentation.signIn.SignInViewModel
import com.example.moviesapp.screen.AllMovies
import com.example.moviesapp.presentation.signIn.UserData
import com.example.moviesapp.screen.AnimatedSplashScreen
import com.example.moviesapp.screen.categoryMoviesCreen.CategoryMoviesScreen
import com.example.moviesapp.screen.comingSoonScreen.ComingSoonScreen
import com.example.moviesapp.screen.playMovieScreen.Film
import com.example.moviesapp.screen.homeScreen.HomeViewModel
import com.example.moviesapp.screen.userScreen.UserScreen
import com.example.myapplication.model.NavigationItem
import com.example.myapplication.screen.mainScreen.MainViewModel
import com.plcoding.composegooglesignincleanarchitecture.presentation.profile.ProfileScreen
import kotlinx.coroutines.launch

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun BottomBar(
    mainViewModel: MainViewModel,
    navController: NavController,
    bottomBarState: Boolean
) {
    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntry?.destination
    AnimatedVisibility(visible = bottomBarState,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            BottomNavigation(backgroundColor = Color.DarkGray) {
                mainViewModel.uiState.value.navigationItems.forEach { item ->
                    AddItem(
                        screen = item,
                        currentDestination = currentDestination,
                        navController = navController
                    )
                }
            }
        })
}

@UnstableApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BottomBarAnimationApp(
    googleAuthUiClient: GoogleAuthUiClient,
) {
    val mainViewModel: MainViewModel = hiltViewModel()
    val homeViewModel: HomeViewModel = hiltViewModel()
    val moviesState = homeViewModel.movies.collectAsState()
    val viewFavouriteModel: FavouriteMoviesModel = viewModel()
    val favouriteMovies by viewFavouriteModel.favouriteMovies.collectAsState(initial = emptyList())

    val coroutine = rememberCoroutineScope()
    val context = LocalContext.current
    BottomBarAnimationTheme {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = NavigationItem.AnimatedSplash.route,) {
            composable(NavigationItem.Home.route) {
                HomeScreen(
                    mainViewModel = mainViewModel,
                    navController = navController,
                    bottomBarState = true,
                    movies = moviesState.value,
                    movieFavourites = favouriteMovies.orEmpty()
                )
            }

            composable(
                MovieBookNavigation.route,
                arguments = listOf(navArgument(MovieBookNavigation.movieArg) {
                    nullable = true
                    type = MoviesNavType()
                })
            ) {
                val movie = MovieBookNavigation.from(it)
                Film(movie = movie!!,
                    navController = navController,
                    moviesState.value,
                    movieFavourites = favouriteMovies,
                    viewModel = viewFavouriteModel,
                    googleAuthUiClient = googleAuthUiClient
                )
            }
            composable(NavigationItem.Ranking.route) {
                RankingScreen(
                    mainViewModel = mainViewModel,
                    navController = navController,
                    movies = moviesState.value,
                    movieFavourites = favouriteMovies,
                    viewModel = viewFavouriteModel,
                    googleAuthUiClient = googleAuthUiClient
                )
            }
            composable(NavigationItem.Search.route) {
                SearchScreen(
                    mainViewModel = mainViewModel,
                    navController = navController,
                    movies = moviesState.value,
                )
            }

            composable(
                CategoryMovieBookNavigation.route,
                arguments = listOf(navArgument(CategoryMovieBookNavigation.categoryMovieArg) {
                    nullable = true
                    type = CategoryMovieNavType()
                })
            ) {
                val category = CategoryMovieBookNavigation.from(it)
                CategoryMoviesScreen(category!!, navController, moviesState.value)
            }
            composable(NavigationItem.ComingSoon.route) {
                ComingSoonScreen(
                    mainViewModel = mainViewModel,
                    navController = navController,
                    movies = moviesState.value,
                    movieFavourites = favouriteMovies,
                    viewModel = viewFavouriteModel,
                    googleAuthUiClient = googleAuthUiClient
                )
            }
            composable(NavigationItem.User.route) {
                UserScreen(
                    mainViewModel = mainViewModel,
                    navController = navController,
                    googleAuthUiClient,
                    onSignOut = {
                        coroutine.launch {
                            googleAuthUiClient.signOut()
                            Toast.makeText(
                                context,
                                "Đăng xuất thành công",
                                Toast.LENGTH_LONG
                            ).show()
                            navController.popBackStack()
                            navController.navigate(NavigationItem.User.route)
                        }
                    }
                )
            }
            composable(NavigationItem.AnimatedSplash.route) {
                AnimatedSplashScreen(navController)
            }
            composable("signIn") {
                val viewModel: SignInViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()
                val launcherGoogle = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.StartIntentSenderForResult(),
                    onResult = { result ->
                        if (result.resultCode == ComponentActivity.RESULT_OK) {
                            coroutine.launch {
                                val signInResult = googleAuthUiClient.signInWithIntent(
                                    intent = result.data ?: return@launch
                                )
                                viewModel.onSignInResult(signInResult)
                            }
                        }
                    }
                )
                LaunchedEffect(key1 = state.isSignInSuccessful) {
                    if (state.isSignInSuccessful) {
                        Toast.makeText(
                            context,
                            "Đăng nhập thành công",
                            Toast.LENGTH_LONG
                        ).show()
                        navController.navigate(NavigationItem.User.route)
                        viewModel.resetState()
                    }
                }
                SignInScreen(
                    state = state,
                    onSignInGoogleClick = {
                        coroutine.launch {
                            val signInIntentSender = googleAuthUiClient.signIn()
                            launcherGoogle.launch(
                                IntentSenderRequest.Builder(
                                    signInIntentSender ?: return@launch
                                ).build()
                            )
                        }
                    },
                    navController
                )
            }
            composable("profile") {
                var userData by remember { mutableStateOf<UserData?>(null) }

                LaunchedEffect(key1 = Unit) {
                    userData = googleAuthUiClient.getSignedInUser()
                }
                ProfileScreen(
                    userData = userData,
                    onSignOut = {
                        coroutine.launch {
                            googleAuthUiClient.signOut()
                            Toast.makeText(
                                context,
                                "Đăng xuất thành công",
                                Toast.LENGTH_LONG
                            ).show()
                            navController.popBackStack()
                        }
                    }
                )
            }

            composable("allmovies/{title}", arguments = listOf(navArgument("title") {
                type = NavType.StringType
            })) { navBackStackEntry ->
                AllMovies(
                    movies = moviesState.value,
                    title = navBackStackEntry.arguments?.getString("title")!!, navController
                )
            }

            composable("favourite") {
                FavouriteList(
                    movies = favouriteMovies.orEmpty(),
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: NavigationItem, currentDestination: NavDestination?, navController: NavController
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(Color.Transparent)
            .clickable(onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }

            })
    ) {
        Row(
            modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)

        ) {
            Icon(
                painter = painterResource(id = if (selected) screen.image else screen.imageSelected),
                contentDescription = "icon",
                tint = Color.White
            )
            AnimatedVisibility(visible = selected) {
                Text(
                    text = screen.title, color = Color.White
                )
            }
        }
    }
}
