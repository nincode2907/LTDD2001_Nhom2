package com.example.moviesapp.screen.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import codes.andreirozov.bottombaranimation.ui.theme.fontFamilyHeading
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviesapp.ShareViewModel
import com.example.moviesapp.model.Movie
import com.example.moviesapp.screen.homeScreen.component.ButtonPlay
import com.example.moviesapp.screen.homeScreen.component.CarouselListFilms
import com.example.moviesapp.screen.homeScreen.component.IconBackBlur
import com.example.moviesapp.screen.homeScreen.component.IconDetail
import com.example.moviesapp.screen.homeScreen.component.InfoCategoryFilm
import com.example.moviesapp.screen.homeScreen.component.InfoTopicFilm
import com.example.moviesapp.screen.homeScreen.component.StyleStatic
import com.example.moviesapp.screen.homeScreen.component.listFilms
import com.example.myapplication.model.NavigationItem




@Composable
fun Film(
    shareViewModel: ShareViewModel,
    navController: NavController,
    movies:List<Movie>
) {
    val movie = shareViewModel.movie
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(StyleStatic.primaryModeColor)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                Box(
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(movie!!.image)
                                .crossfade(true)
                                .build(),
                            contentDescription = movie.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(240.dp)
                                .padding(bottom = 16.dp),
                            contentScale = ContentScale.Crop
                        )
                        IconBackBlur(
                            icon = Icons.Default.PlayArrow,
                            colorIcon = StyleStatic.primaryTextColor,
                            size = "big",
                            onClick = {
                                navController.popBackStack(NavigationItem.Home.route,false)
                            }
                        )
                    }

                    repeat(3) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            StyleStatic.primaryModeColor
                                        )
                                    )
                                )
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = movie!!.name.toString(),
                        style = StyleStatic.textCommonStyle.copy(
                            fontSize = 38.sp,
                            fontFamily = fontFamilyHeading,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Row(
                        modifier = Modifier.padding(top = 12.dp, bottom = 4.dp)
                    ) {
                        val styleInRow = StyleStatic.textCommonStyle.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = StyleStatic.blurTextWhiteColor
                        )
                        Text(
                            text = movie.releaseDate.toString(),
                            style = styleInRow
                        )

                        Text(
                            text = "•",
                            style = styleInRow,
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )

                        Text(
                            text = movie.episodeTotal.toString(),
                            style = styleInRow
                        )

                        Text(
                            text = "•",
                            style = styleInRow,
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )

                        Text(
                            text = movie.time.toString(),
                            style = styleInRow
                        )
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

                    InfoTopicFilm(
                        topic = "Quốc gia",
                        infomation = movie.country.toString()
                    )

                    InfoCategoryFilm(
                        topic = "Thể loại",
                        infomation = movie.category!!

                    )

                    ButtonPlay(
                        onClick = {},
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                            .fillMaxWidth(),
                        fSize = 18
                    )

                    Row(modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)) {
                        IconDetail(
                            icon = Icons.Outlined.Add,
                            description = "Thêm vào DS",
                            modifier = Modifier.padding(horizontal = 5.dp)
                        )

                        IconDetail(
                            icon = Icons.Outlined.Share,
                            description = "Chia sẻ",
                            modifier = Modifier.padding(horizontal = 5.dp)
                        )

                        IconDetail(
                            icon = Icons.Default.FavoriteBorder,
                            description = "Yêu thích",
                            modifier = Modifier.padding(horizontal = 5.dp)
                        )
                    }
                }
                CarouselListFilms(movie = movie!!,navController,movies, shareViewModel = shareViewModel)
                Spacer(modifier = Modifier.height(30.dp))
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconBackBlur(
                icon = Icons.Default.ArrowBack,
                colorIcon = StyleStatic.primaryTextColor,
                size = "small",
                onClick = {
                    navController.popBackStack()
                }
            )

            IconBackBlur(
                icon = Icons.Default.Close,
                colorIcon = StyleStatic.primaryTextColor,
                size = "small",
                onClick = {
                    navController.navigate(NavigationItem.Home.route)
                }
            )
        }
    }
}

//@Preview(showBackground = true, widthDp = 564, heightDp = 1254)
//@Composable
//fun PreviewHome() {
//    Surface(
//        modifier = Modifier.fillMaxSize(),
//        color = colorResource(id = R.color.dark)
//    ) {
//        Film(filmId = "OPfRdHT",navController)
//    }
//}