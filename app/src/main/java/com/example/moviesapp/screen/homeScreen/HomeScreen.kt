package com.example.movieapp.screen.homeScreen


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviesapp.R
import com.example.moviesapp.ShareViewModel
import com.example.moviesapp.model.Movie
import com.example.moviesapp.screen.homeScreen.component.Carousel
import com.example.myapplication.screen.mainScreen.MainViewModel
import com.example.petadoption.bottomnav.BottomBar
import com.example.moviesapp.screen.homeScreen.component.ListFilmHorizontal
import com.example.moviesapp.screen.homeScreen.component.ListFilmTop5
import com.example.myapplication.screen.PlayMovieScreen.PlayMovieViewModel


@SuppressLint(
    "StateFlowValueCalledInComposition", "CoroutineCreationDuringComposition",
    "UnsafeOptInUsageError"
)
@Composable
fun HomeScreen(
    mainViewModel: MainViewModel,
    navController: NavController,
    bottomBarState:Boolean,
    movies: List<Movie>
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
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .background(colorResource(id = R.color.dark)),
            verticalArrangement = Arrangement.Top

        ) {
            item {
                Carousel(
                    movies.filter { it.outstanding == true },
                    navController,

                )
                ListFilmHorizontal(
                    movies.shuffled(),
                    categoryFilms = "Phim Thể Loại Top 1 Khu Vực",
                    navController
                )
                ListFilmTop5(
                    movies.shuffled(),
                    navController = navController,
                )
                ListFilmHorizontal(
                    movies.shuffled(),
                    categoryFilms = "Trinh Thám",
                    navController,
                )
                ListFilmHorizontal(
                    movies.shuffled(),
                    navController = navController,
                )
                ListFilmHorizontal(
                    movies.shuffled(),
                    categoryFilms = "Phim Chiếu Rạp Mới",
                    navController,
                )
                Spacer(modifier = Modifier.height(50.dp))
            }
        }
    }
}
