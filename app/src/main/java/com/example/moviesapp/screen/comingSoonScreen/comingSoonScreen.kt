@file:Suppress("DEPRECATION")

package com.example.moviesapp.screen.comingSoonScreen

import android.R.attr.text
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import codes.andreirozov.bottombaranimation.ui.theme.fontFamilyHeading
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviesapp.R
import com.example.moviesapp.model.Movie
import com.example.moviesapp.model.MovieBookNavigation
import com.example.moviesapp.presentation.favourite.FavouriteMoviesModel
import com.example.moviesapp.presentation.signIn.GoogleAuthUiClient
import com.example.moviesapp.screen.homeScreen.component.NotConnected
import com.example.moviesapp.screen.homeScreen.component.StyleStatic
import com.example.moviesapp.screen.mainScreen.Main
import com.example.moviesapp.screen.mainScreen.Main.checkNetworkConnectivity
import com.example.myapplication.screen.mainScreen.MainViewModel
import com.example.petadoption.bottomnav.BottomBar
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.sql.Timestamp
import java.util.Calendar


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComingSoonScreen(
    mainViewModel: MainViewModel,
    navController: NavController,
    movies: List<Movie>,
    movieFavourites: List<Movie>,
    viewModel: FavouriteMoviesModel,
    googleAuthUiClient: GoogleAuthUiClient
) {
    val currentDate = LocalDate.now()
    val context = LocalContext.current

    var isConnected by remember{ mutableStateOf(Main.checkNetworkConnectivity(context))}

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(Color.Black),
                title = {
                    Text(
                        text = "Sắp ra mắt",
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
                Modifier
                    .padding(paddingValues = paddingValues)
                    .background(Color.Black)
            ) {
                items(movies) { movie ->
                    val isFavourite = movie.id?.let { movieId ->
                        movieFavourites.any { it.id == movieId }
                    } ?: false
                    MovieList(
                        movie = movie,
                        isFavouriteInit = isFavourite,
                        viewModel = viewModel,
                        navController = navController,
                        googleAuthUiClient = googleAuthUiClient
                    ) {
                        navController.navigate(MovieBookNavigation.createRoute(movie = movie))
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
@SuppressLint("NewApi")
@Composable
fun MovieList(
    movie: Movie,
    isFavouriteInit: Boolean,
    viewModel: FavouriteMoviesModel,
    navController: NavController,
    googleAuthUiClient: GoogleAuthUiClient,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    var isFavourite by remember { mutableStateOf(isFavouriteInit) }

    Column(
        modifier = Modifier
            .background(Color.Black)
            .padding(10.dp)
            .clickable(onClick = onClick),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            Divider(
                modifier = Modifier
                    .height(height = 45.dp)
                    .width(width = 3.dp),
                color = Color(0xFF157AFF)
            )
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                val month = movie.releaseDate?.toDate()?.month?.plus(1) ?: 9
                Text(
                    text = movie.releaseDate?.toDate()?.day.toString(),
                    color = Color.White,
                    fontSize = 17.sp, fontWeight = FontWeight.Bold
                )
                Text(
                   text = "Tháng ${if (month < 10) "0$month" else "$month"}",
                    color = Color(0xFFB3B3B3),
                    fontSize = 15.sp, fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Italic
                )
            }
        }
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(movie.image)
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(10.dp))
                .shadow(elevation = 25.dp),
            contentScale = ContentScale.Crop
        )
        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp),
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    modifier = Modifier.weight(2f),
                    text = movie.name.toString(),
                    style = StyleStatic.textCommonStyle.copy(
                        fontSize = 30.sp,
                        fontFamily = fontFamilyHeading,
                        fontWeight = FontWeight.Bold
                    )

                )
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    IconButtonView(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .width(90.dp),
                        img = if(!isFavourite) R.drawable.ic_save1 else R.drawable.ic_save,
                        title = "Save"
                    ) {
                        if (googleAuthUiClient.getSignedInUser() != null) {
                            viewModel.viewModelScope.launch {
                                viewModel.updateFavourite(movie)
                            }
                            isFavourite = !isFavourite
                        } else {
                            navController.navigate("signIn")
                        }
                    }

                    IconButtonView(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .width(90.dp), img = R.drawable.ic_share, title = "Share"
                    ) {
                        val text = "Tóc phai màu, ốm đau nhiều."

                        val sendIntent = Intent()
                        sendIntent.action = Intent.ACTION_SEND
                        sendIntent.putExtra(
                            Intent.EXTRA_TEXT,
                            text
                        )
                        sendIntent.type = "text/plain"
                        context.startActivity(sendIntent)
                    }

                }
            }
        }

        Text(
            text = movie.description.toString(),
            style = StyleStatic.textCommonStyle.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                textIndent = TextIndent(6.sp)
            ),
            maxLines = 4,
            modifier = Modifier.padding(vertical = 8.dp),
            overflow = TextOverflow.Ellipsis
        )


    }


}

@Composable
fun IconButtonView(modifier: Modifier, img: Int, title: String, onClick: () -> Unit) {
    IconButton(modifier = modifier, onClick = onClick)
    {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(id = img),
                contentDescription = "",
                modifier = Modifier
                    .size(30.dp), tint = Color.White
            )
            Text(
                text = title,
                fontSize = 12.sp,
                textAlign = TextAlign.Start,
                color = Color.White
            )
        }
    }
}




