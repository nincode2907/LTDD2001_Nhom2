package com.example.moviesapp.presentation.favourite

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.moviesapp.model.Movie
import com.example.moviesapp.model.MovieBookNavigation
import com.example.moviesapp.screen.homeScreen.component.FilmInFavourite
import com.example.moviesapp.screen.homeScreen.component.StyleStatic
import com.example.myapplication.model.NavigationItem
import com.google.firebase.auth.FirebaseAuth

@Composable
fun FavouriteList(
    movies: List<Movie>,
    navController: NavController
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .height(40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 10.dp ,end = 10.dp)
                    .clip(RoundedCornerShape(percent = 50))
                    .size(26.dp)
                    .clickable {
                        navController.navigate(NavigationItem.User.route)
                    }
                ,
                tint = StyleStatic.primaryTextColor
            )
            Text(
                text = "Danh sách phim yêu thích",
                color = StyleStatic.primaryTextColor,
                style = TextStyle(fontSize = 18.sp)
            )
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(
                start = 10.dp,
                top = 8.dp,
                end = 10.dp,
                bottom = 12.dp),
            modifier = Modifier.padding(top = 40.dp)
        ) {
            Log.d("MoviesFL: " ,movies.toString())
            items(movies) {it ->
                FilmInFavourite(imageUrl = it.image.toString(),
                    modifier = Modifier
                        .height(180.dp)
                        .padding(horizontal = 4.dp, vertical = 4.dp)
                        .fillMaxWidth(),
                    onClick = {
                    navController.navigate(MovieBookNavigation.createRoute(movie = it))
                })
            }
        }
    }
}