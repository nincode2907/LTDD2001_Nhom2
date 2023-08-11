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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moviesapp.R
import com.example.moviesapp.ShareViewModel
import com.example.moviesapp.model.Movie
import com.example.moviesapp.screen.homeScreen.component.Carousel
import com.example.myapplication.screen.mainScreen.MainViewModel
import com.example.petadoption.bottomnav.BottomBar
import com.example.moviesapp.screen.homeScreen.HomeViewModel
import com.example.moviesapp.screen.homeScreen.component.ListFilmHorizontal
import com.example.moviesapp.screen.homeScreen.component.ListFilmTop5


@SuppressLint(
    "StateFlowValueCalledInComposition", "CoroutineCreationDuringComposition",
    "UnsafeOptInUsageError"
)
@Composable
fun HomeScreen(
    mainViewModel: MainViewModel,
    navController: NavController,
    bottomBarState: MutableState<Boolean>,
    shareViewModel: ShareViewModel,
    movies:List<Movie>
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
                    shareViewModel
                )
                ListFilmHorizontal(
                    movies.shuffled(),
                    categoryFilms = "Phim Thể Loại Top 1 Khu Vực",
                    navController,
                    shareViewModel = shareViewModel
                )
                ListFilmTop5(
                    movies.shuffled(),
                    navController = navController,
                    shareViewModel = shareViewModel
                )
                ListFilmHorizontal(
                    movies.shuffled(),
                    categoryFilms = "Trinh Thám",
                    navController,
                    shareViewModel = shareViewModel
                )
                ListFilmHorizontal(
                    movies.shuffled(),
                    navController = navController,
                    shareViewModel = shareViewModel
                )
                ListFilmHorizontal(
                    movies.shuffled(),
                    categoryFilms = "Phim Chiếu Rạp Mới",
                    navController,
                    shareViewModel = shareViewModel
                )
                Spacer(modifier = Modifier.height(50.dp))
            }
        }
    }
}

@Composable
fun App(
    navController: NavController,
) {
    LazyColumn(
        verticalArrangement = Arrangement.Top
    ) {
        item {
            //  Carousel(homeViewModel, navController)
//            ListFilmHorizontal(films = listFilms.shuffled(), categoryFilms = "Phim Thể Loại Top 1 Khu Vực", navController)
//            ListFilmTop5(films = listFilms, navController = navController)
//            ListFilmHorizontal(films = listFilms.reversed(), categoryFilms = "Trinh Thám", navController)
//            ListFilmHorizontal(films = listFilms.shuffled(), navController = navController)
//            ListFilmHorizontal(films = listFilms.shuffled(), categoryFilms = "Phim Chiếu Rạp Mới", navController)
//            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

//@Composable
//fun WaterDropAnimation(initialRadius: Float, onAnimationEnd: () -> Unit) {
//    val animationSpec = remember { tween<Float>(durationMillis = 500) }
//
//    val radiusAnimatable = remember { Animatable(initialRadius) }
//
//    LaunchedEffect(Unit) {
//        radiusAnimatable.animateTo(300f, animationSpec)
//        onAnimationEnd()
//    }
//
//    Surface(
//        modifier = Modifier
//            .padding(16.dp)
//            .graphicsLayer {
//                scaleX = 1 + radiusAnimatable.value / 1000 // Adjust the scale of the drop
//                scaleY = 1 + radiusAnimatable.value / 1000 // Adjust the scale of the drop
//            },
//        shape = CircleShape,
//        color = Color.Blue
//    ) {
//        Canvas(modifier = Modifier.fillMaxSize()) {
//            drawRoundRect(
//                color = Color.Cyan,
//                size = Size(radiusAnimatable.value * 2, radiusAnimatable.value * 2),
//                cornerRadius = CornerRadius(radiusAnimatable.value)
//            )
//        }
//    }
//}

//@Preview(showBackground = true, widthDp = 320, heightDp = 640)
//@Preview(showBackground = true, widthDp = 480, heightDp = 800)
//@Preview(showBackground = true, widthDp = 720, heightDp = 1280)
//@Preview(showBackground = true, widthDp = 1080, heightDp = 1920)
//@Preview(showBackground = true, widthDp = 1440, heightDp = 2560)
//@Preview(showBackground = true, widthDp = 1024, heightDp = 768)
//@Preview(showBackground = true, widthDp = 1366, heightDp = 768)
//@Preview(showBackground = true, widthDp = 564, heightDp = 1254)
//@Composable
//fun PreviewHome() {
//    Surface(
//        modifier = Modifier.fillMaxSize(),
//        color = colorResource(id = R.color.dark)
//    ) {
//        App()
//    }
//}