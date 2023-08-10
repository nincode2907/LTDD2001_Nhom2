package com.example.moviesapp.screen.playMovieScreen

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.ui.PlayerView
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

import com.example.moviesapp.screen.homeScreen.component.InfoSpaceDot
import com.example.moviesapp.screen.homeScreen.component.InfoTopicFilm
import com.example.moviesapp.screen.homeScreen.component.StyleStatic
import com.example.moviesapp.screen.homeScreen.component.listFilms
import com.example.myapplication.model.NavigationItem
import com.example.myapplication.screen.PlayMovieScreen.PlayMovieViewModel


@Composable
fun Film(
    shareViewModel: ShareViewModel,
    navController: NavController,
    movies: List<Movie>
) {
    val movie = shareViewModel.movie
    val viewModel: PlayMovieViewModel = hiltViewModel()
    var lifecycle by remember {
        mutableStateOf(Lifecycle.Event.ON_CREATE)
    }
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycle = event
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    var isFullScreen by remember {
        mutableStateOf(true)
    }

    Scaffold() { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(paddingValues)) {
            AndroidView(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black),
                factory = { context ->
                    PlayerView(context).also {
                        it.player = viewModel.player
                        it.setFullscreenButtonClickListener { view ->
                            with(context) {
                                if (isFullScreen) {
                                    setScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
                                } else {
                                    setScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                                }
                                isFullScreen = !isFullScreen
                            }
                        }
                    }
                },
                update = {
                    when (lifecycle) {
                        Lifecycle.Event.ON_PAUSE -> {
                            it.onPause()
                            it.player?.pause()
                        }

                        Lifecycle.Event.ON_RESUME -> {
                            it.onResume()
                        }


                        else -> Unit
                    }
                },
            )
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
                                    text = movie.releaseDate?.toDate().toString(),
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
                                onClick = {

                                },
                                modifier = Modifier
                                    .padding(vertical = 12.dp)
                                    .fillMaxWidth(),
                                fSize = 18
                            )

                            Row(modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)) {
                                IconDetail(
                                    icon = Icons.Outlined.Add,
                                    description = "Thêm vào DS",
                                    colorText = StyleStatic.blurTextWhiteColor,
                                    modifier = Modifier.padding(end = 16.dp)
                                )

                                IconDetail(
                                    icon = Icons.Outlined.Share,
                                    description = "Chia sẻ",
                                    colorText = StyleStatic.blurTextWhiteColor,
                                    modifier = Modifier.padding(end = 16.dp)
                                )
                                IconDetail(
                                    icon = Icons.Default.FavoriteBorder,
                                    description = "Yêu thích",
                                    colorText = StyleStatic.blurTextWhiteColor,
                                    modifier = Modifier.padding(end = 16.dp)
                                )
                            }
                        }
                        CarouselListFilms(
                            movie = movie!!,
                            navController,
                            movies,
                            shareViewModel = shareViewModel
                        )
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


    }


}


fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

fun Context.setScreenOrientation(orientation: Int) {
    val activity = this.findActivity() ?: return
    activity.requestedOrientation = orientation
    if (orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
        hideSystemUi()
    } else {
        showSystemUi()
    }
}

fun Context.hideSystemUi() {
    val activity = this.findActivity() ?: return
    val window = activity.window ?: return
    WindowCompat.setDecorFitsSystemWindows(window, false)
    WindowInsetsControllerCompat(window, window.decorView).let { controller ->
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}

fun Context.showSystemUi() {
    val activity = this.findActivity() ?: return
    val window = activity.window ?: return
    WindowCompat.setDecorFitsSystemWindows(window, true)
    WindowInsetsControllerCompat(
        window,
        window.decorView
    ).show(WindowInsetsCompat.Type.systemBars())
}