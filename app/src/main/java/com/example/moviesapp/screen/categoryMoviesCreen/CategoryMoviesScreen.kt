package com.example.moviesapp.screen.categoryMoviesCreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movieapp.screen.searchScreen.ItemMovieView
import com.example.moviesapp.data.moviesData.MovieRepository
import com.example.moviesapp.model.CategoryMovie
import com.example.moviesapp.model.Movie
import com.example.moviesapp.model.MovieBookNavigation
import com.example.moviesapp.screen.homeScreen.component.ItemMovieTop5
import com.example.moviesapp.screen.homeScreen.component.StyleStatic

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryMoviesScreen(
    categoryMovie: CategoryMovie,
    navController: NavController,
    movies: List<Movie>,

) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(Color.Black),
                title = {
                    TopBarScreen(title = categoryMovie.name)
                },
                navigationIcon = {
                    IconButton(onClick = {

                        navController.popBackStack()
                    }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon", tint = Color.White)
                    }
                },
            )
        }
    ) { paddingValues ->
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(paddingValues),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp)
        ) {

            item(span = { GridItemSpan(2) }) {
                Top5Movies(
                    categoryMovie,
                    movies.filter { categoryMovie.name in it.category.orEmpty() }
                        .sortedByDescending { it.view ?: 0 }.take(5),
                    navController
                )
            }

            item(span = { GridItemSpan(2) }) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Có thể bạn quan tâm",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White, textAlign = TextAlign.Start
                )
            }
            items(movies.filter { categoryMovie.name in it.category.orEmpty() }) { it ->

                ItemMovieView(it) {
                    navController.navigate(MovieBookNavigation.createRoute(movie = it))

                }
            }
        }
    }
}

@Composable
fun TopBarScreen(title: String) {
    Text(
        text = title,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White
    )
}

@Composable
fun Top5Movies(categoryMovie: CategoryMovie, movies: List<Movie>, navController: NavController) {
    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Top 5 phim " + categoryMovie.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White, textAlign = TextAlign.Start
        )
        LazyRow() {
            itemsIndexed(movies.take(5)) { index, film ->
                ItemMovieTop5(film.image.toString(), index + 1, onClick = {
                    navController.navigate(MovieBookNavigation.createRoute(movie = film))

                })
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun Prieview() {
}