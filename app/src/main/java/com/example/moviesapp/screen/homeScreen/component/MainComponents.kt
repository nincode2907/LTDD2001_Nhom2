package com.example.moviesapp.screen.homeScreen.component

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.moviesapp.R
import com.example.moviesapp.ShareViewModel
import com.example.moviesapp.model.Movie
import com.example.moviesapp.screen.homeScreen.component.StyleStatic.textCommonStyle
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun Carousel(
    movies: List<Movie>,
    navController: NavController,
    shareViewModel: ShareViewModel,
) {

    val pagerState = rememberPagerState()
    var scope = rememberCoroutineScope()
    var liked by remember { mutableStateOf(false) }
    val configuration = LocalConfiguration.current

    // Chiều cao sẽ bằng 70% kích thước màn hình
    val screenHeight = configuration.screenHeightDp.dp
    val height = screenHeight * 0.8f

    // Chiều cao của ảnh
    val imageHeight = height - 80.dp

    var colorLikeIcon =
        if (liked) colorResource(id = R.color.tym) else StyleStatic.primaryTextColor
//    LaunchedEffect(Unit) {
//        while (true) {
//            delay(4000)
//            scope.launch {
//                pagerState.animateScrollToPage(pagerState.currentPage + 1)
//            }
//        }
//    }
    Column() {
        HorizontalPager(
            count = movies.size,
            state = pagerState,
            modifier = Modifier.height(height),
            verticalAlignment = Alignment.Top
        ) { page ->
            ItemPoster(
                imageUrl = movies[page].image.toString(),
                heightImg = imageHeight,
                navController,
                onClick = {
//                    shareViewModel.addMovie(newMovie = movies[page])
                    navController.navigate("movie")
                })
        }
        Row(
            Modifier
                .height(50.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(movies.size) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color.White else colorResource(
                        id = R.color.gray
                    )
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(8.dp)
                        .clickable {
                            scope.launch {
                                pagerState.scrollToPage(iteration)
                            }
                        }
                )
            }
        }
    }
}

//@Composable
//fun ListFilmHorizontal(
//    films: List<FilmInfo> ,
//    categoryFilms: String = "Phim Mới",
//    navController: NavController
//) {
//    Column(modifier = Modifier.padding(15.dp)) {
//        TitleRowViewMovie(categoryFilms)
//        LazyRow(
//            horizontalArrangement = Arrangement.spacedBy(8.dp)
//        ) {
//            items(films) { film ->
//                FilmInList(imageUrl = film.poster, onClick = {
//                    navController.navigate("FilmDetail/" + film.id)
//                })
//            }
//            item {
//                FilmSeeMore()
//            }
//        }
//    }
//}
//
//@Composable
//fun ListFilmTop5(
//    films: List<FilmInfo> ,
//    navController: NavController
//) {
//    var index = 1
//    Column(modifier = Modifier.padding(15.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
//        Text(
//            text = "Phim Top 5 Hôm Nay",
//            style = StyleStatic.textCommonStyle.copy(
//                fontSize = 16.sp,
//                fontWeight = FontWeight.Medium
//            ),
//            modifier = Modifier
//                .padding(horizontal = 18.dp)
//        )
//
//        LazyRow() {
//            items(films.take(5)) {film ->
//                ItemMovieTop5(film.poster, index, onClick = {
//                    navController.navigate("FilmDetail/" + film.id)
//                })
//                index++
//            }
//        }
//    }
//}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CarouselListFilms(
    movie: Movie,
    navController: NavController,
    movies: List<Movie>,
    shareViewModel: ShareViewModel
) {
    val listTags = listOf("Phim liên quan", "Trailer")
    val pagerState = rememberPagerState(initialPage = 0)
    var scope = rememberCoroutineScope()

    val styleActive = textCommonStyle.copy(fontWeight = FontWeight.Bold, fontSize = 17.sp)

    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(Color.Black)
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.65f)
                    .fillMaxHeight()
                    .background(Color.White, RoundedCornerShape(8.dp))
            )
        }
        Spacer(modifier = Modifier.height(6.dp))


        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            ) {
                repeat(listTags.size) { it ->
                    val styleText = if (pagerState.currentPage == it) styleActive
                    else textCommonStyle.copy(fontWeight = FontWeight.Normal, fontSize = 17.sp)
                    val colorSpacer =
                        if (pagerState.currentPage == it) StyleStatic.primaryTextColor else Color.Transparent

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .wrapContentSize()
                            .width(150.dp)
                    ) {
                        Text(
                            text = listTags[it],
                            style = styleText,
                            modifier = Modifier
                                .clickable {
                                    scope.launch {
                                        pagerState.animateScrollToPage(it)
                                    }
                                }
                                .animateContentSize()
                        )
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(3.dp)
                                .background(color = colorSpacer)
                                .animateContentSize()
                        )
                    }
                }
            }

            HorizontalPager(
                count = listTags.size,
                state = pagerState
            ) { page ->
                when (page) {
                    0 -> RelatedMovies(movies, navController, shareViewModel = shareViewModel)
                    1 -> YoutubeTrailer(movie.trailer.toString())
                }
            }
        }
    }
}

@Composable
fun ListFilmHorizontal(
    movies: List<Movie>,
    categoryFilms: String = "Phim Mới",
    navController: NavController,
    shareViewModel: ShareViewModel
) {
    Column(modifier = Modifier.padding(15.dp)) {
        TitleRowViewMovie(categoryFilms)
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(movies) { film ->
                FilmInList(imageUrl = film.image.toString(), onClick = {
//                    shareViewModel.addMovie(newMovie = film)
                    navController.navigate("movie")
                })
            }
            item {
                FilmSeeMore()
            }
        }
    }
}
@Composable
fun TitleRowViewMovie(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = StyleStatic.textCommonStyle.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "Xem tất cả",
            tint = StyleStatic.primaryTextColor
        )
    }
}

@Composable
fun ListFilmTop5(
    movies: List<Movie>,
    navController: NavController,
    shareViewModel: ShareViewModel
) {
    Column(modifier = Modifier.padding(15.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Text(
            text = "Phim Top 5 Hôm Nay",
            style = StyleStatic.textCommonStyle.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier
                .padding(horizontal = 18.dp)
        )
        LazyRow() {
            itemsIndexed(movies.take(5)) { index, film ->
                ItemMovieTop5(film.image.toString(), index+1, onClick = {
//                    shareViewModel.addMovie(newMovie = film)
                    navController.navigate("movie")
                })
            }
        }
    }
}


@Composable
fun RelatedMovies(
    movies: List<Movie>,
    navController: NavController,
    shareViewModel: ShareViewModel
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        for(film in movies) {
            ItemRelatedFilm(film, onClick = {
//                shareViewModel.addMovie(film)
                navController.navigate("movie")
            })
        }
    }


}
