package com.example.moviesapp

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moviesapp.model.Movie
import com.example.moviesapp.model.MovieBookNavigation
import com.example.moviesapp.model.MoviesNavType
import com.example.moviesapp.screen.homeScreen.HomeViewModel
import com.example.myapplication.screen.mainScreen.MainScreen
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

//import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition", "SuspiciousIndentation")
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            window.statusBarColor = ContextCompat.getColor(this, R.color.black)
            WindowInsetsControllerCompat(window, window.decorView).apply {
                hide(WindowInsetsCompat.Type.navigationBars())
                hide(WindowInsetsCompat.Type.statusBars())
                systemBarsBehavior =
                    WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }

             MainScreen()
//            val homeViewModel: HomeViewModel = hiltViewModel()
//            val moviesState = homeViewModel.movies.collectAsState()
//            val navController = rememberNavController()
//            NavHost(navController = navController, startDestination = "home") {
//                composable("home") {
//                    homeTest(moviesState.value, navController)
//                }
//                composable(
//                    MovieBookNavigation.route,
//                    arguments = listOf(navArgument(MovieBookNavigation.movieArg) {
//                        nullable = true
//                        type = MoviesNavType()
//                    })
//                ) {
//                    val movie = MovieBookNavigation.from(it)
//                    ScreenUser(movie!!)
//                }
//            }


//            val db = Firebase.firestore
//            val movie = Movie()

        }
    }
}

@SuppressLint("NewApi")
@Composable
fun ScreenUser(movie: Movie) {

    //val date = movie.releaseDate?.toDate()

    Column {
        Text(text = movie.toString())

        // Text(text = date?.day.toString())
    }
}


@Composable
fun homeTest(movies: List<Movie>, navController: NavController) {
    LazyColumn() {
        items(movies) { it ->
            Log.d("XXX",it.toString())
            Text(text = it.name.toString(), modifier = Modifier.size(100.dp).clickable {
                navController.navigate(MovieBookNavigation.createRoute(movie = it))
            })
        }
    }

//    Button(onClick = {
//        val movie = Movie(name = "abc",)
//        navController.navigate(MovieBookNavigation.createRoute(movie = movie))
//    }) {
//
//    }
}