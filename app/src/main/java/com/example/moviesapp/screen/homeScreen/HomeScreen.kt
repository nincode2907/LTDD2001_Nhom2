package com.example.movieapp.screen.homeScreen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviesapp.R
import com.example.moviesapp.screen.homeScreen.component.Carousel
import com.example.moviesapp.screen.homeScreen.component.ListFilmHorizontal
import com.example.moviesapp.screen.homeScreen.component.ListFilmTop5
import com.example.moviesapp.screen.homeScreen.component.listFilms
import com.example.myapplication.screen.mainScreen.MainViewModel
import com.example.petadoption.bottomnav.BottomBar

@Composable
fun HomeScreen(
    mainViewModel: MainViewModel,
    navController: NavController,
    bottomBarState: MutableState<Boolean>
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
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .background(colorResource(id = R.color.dark))
        ) {
            App(navController)
        }
    }
}

@Composable
fun App(
    navController: NavController,
    modifier: Modifier = Modifier
        .fillMaxSize()
) {
    LazyColumn(
        verticalArrangement = Arrangement.Top
    ) {
        item {
            Carousel(films = listFilms.shuffled(), navController)
            ListFilmHorizontal(films = listFilms.shuffled(), categoryFilms = "Phim Thể Loại Top 1 Khu Vực", navController)
            ListFilmTop5(films = listFilms, navController = navController)
            ListFilmHorizontal(films = listFilms.reversed(), categoryFilms = "Trinh Thám", navController)
            ListFilmHorizontal(films = listFilms.shuffled(), navController = navController)
            ListFilmHorizontal(films = listFilms.shuffled(), categoryFilms = "Phim Chiếu Rạp Mới", navController)
            Spacer(modifier = Modifier.height(50.dp))
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