package com.example.movieapp.screen.rankingScreen

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviesapp.R
import com.example.moviesapp.model.Movie
import com.example.moviesapp.model.MovieBookNavigation
import com.example.moviesapp.presentation.favourite.FavouriteMoviesModel
import com.example.moviesapp.presentation.signIn.GoogleAuthUiClient
import com.example.moviesapp.screen.homeScreen.component.NotConnected
import com.example.moviesapp.screen.mainScreen.Main.checkNetworkConnectivity
import com.example.myapplication.screen.mainScreen.MainViewModel
import com.example.petadoption.bottomnav.BottomBar
import com.example.rank.RankingListItem

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun RankingScreen(
    mainViewModel: MainViewModel,
    navController: NavController,
    movies: List<Movie>,
    movieFavourites: List<Movie>,
    viewModel: FavouriteMoviesModel,
    googleAuthUiClient: GoogleAuthUiClient
) {
    val imageRanking : List<String> = listOf(
        "https://i.ibb.co/v4TWn3P/top1.png",
        "https://i.ibb.co/xYq27KL/top2.png",
        "https://i.ibb.co/n3LvhVZ/top3.png",
        "https://i.ibb.co/G0MVCLz/top4.png",
        "https://i.ibb.co/SmjKxKg/top5.png",
        "https://i.ibb.co/yPP1T3D/top6.png",
        "https://i.ibb.co/wSmCV4T/top7.png",
        "https://i.ibb.co/LtLtPr1/top8.png",
        "https://i.ibb.co/VBZ3KKL/top9.png",
        "https://i.ibb.co/yh4fRcX/top10.png"
    )
    val context = LocalContext.current
    var isConnected by remember { mutableStateOf(checkNetworkConnectivity(context))}

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(colorResource(id = R.color.dark)),
                title = {
                    Text(
                        text = "Xếp hạng",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },

                )
        },
        bottomBar = {
            BottomBar(
                mainViewModel = mainViewModel,
                navController = navController,
                bottomBarState = true
            )
        },
    ) { paddingValues ->
        if(isConnected) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.dark))
                    .padding(paddingValues)
            )
            {
                stickyHeader {
                    Card(
                        modifier = Modifier
                            .fillMaxHeight(),
                        colors = CardDefaults.cardColors(colorResource(id = R.color.dark))
                    ) {
                        Text(
                            text = "Nổi bật",
                            style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                            fontStyle = FontStyle.Italic,
                            color = Color.White,
                            modifier = Modifier.padding(start = 6.dp)
                        )
                        Banner(movies.filter { it.outstanding == true }
                            .sortedByDescending { it.view ?: 0 })
                        Text(
                            text = "Top phim",
                            style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                            fontStyle = FontStyle.Italic,
                            color = Color.White,
                            modifier = Modifier.padding(top = 10.dp, start = 6.dp)
                        )
                    }
                }
                items(movies.take(10).size) { it ->
                    val isFavourite = movies[it].id?.let { movieId ->
                        movieFavourites.any { it.id == movieId }
                    } ?: false
                    RankingListItem(
                        movie = movies[it],
                        isFavourite,
                        viewModel = viewModel,
                        imgTopUrl = imageRanking[it],
                        navController = navController,
                        googleAuthUiClient = googleAuthUiClient
                    ) {
                        navController.navigate(MovieBookNavigation.createRoute(movie = movies[it]))
                    }
                }
            }
        } else {
            NotConnected {
                isConnected = checkNetworkConnectivity(context)
                if(isConnected)
                    Toast.makeText(context, "Đã khôi phục kết nối", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun Banner(movies: List<Movie>) {

    LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {

        items(movies) {movie ->
            Card(
                modifier = Modifier
                    .padding(horizontal = 2.dp, vertical = 15.dp)
                    .height(100.dp)
                    .width(380.dp),
                elevation = CardDefaults.cardElevation(2.dp),
                shape = RoundedCornerShape(corner = CornerSize(5.dp))
            )
            {
                bannerImage(movie = movie)
            }
        }
    }
}
@Composable
fun bannerImage (movie: Movie) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(movie.image)
            .crossfade(true)
            .build(),
        contentDescription = movie.name,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth(1f)
            .height(100.dp))
}




