package com.example.moviesapp.screen.playMovieScreen

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import com.example.moviesapp.model.Movie
import com.example.moviesapp.screen.categoryMoviesCreen.TopBarScreen
import com.example.myapplication.screen.PlayMovieScreen.PlayMovieViewModel
import com.example.myapplication.screen.PlayMovieScreen.VideoDetailAction
import com.example.myapplication.screen.PlayMovieScreen.VideoDetailUiState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@UnstableApi
@Composable
fun Film(
    movie: Movie,
    navController: NavController,
    movies: List<Movie>
) {
    val videoViewModel: PlayMovieViewModel = hiltViewModel(key = movie.id)

    var isFullScreen by remember {
        mutableStateOf(false)
    }

    Scaffold(topBar = {
        AnimatedVisibility(visible = isFullScreen) {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(Color.Black),
                title = {
                    TopBarScreen(title = movie.name.toString())
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
    }) { paddingValues ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            VideoDetailScreen(movie, videoViewModel)

        }
    }
}



@UnstableApi

@Composable
fun VideoDetailScreen(movie: Movie, videoViewModel: PlayMovieViewModel) {
    val uiState = videoViewModel.uiState.collectAsState()
    if (uiState.value == VideoDetailUiState.Default) {
        videoViewModel.handleAction(VideoDetailAction.LoadData(movie.linkUriMovie.toString()))
    }
    VideoDetailScreen(uiState = uiState.value, player = videoViewModel.videoPlayer) { action ->
        videoViewModel.handleAction(action = action)

    }
}

@UnstableApi
@Composable
fun VideoDetailScreen(uiState: VideoDetailUiState, player: Player, handlerAction: (VideoDetailAction) -> Unit
) {
    when (uiState) {
        is VideoDetailUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Loadding")
            }
        }

        is VideoDetailUiState.Success -> {
            VideoDetailScreen(player = player, handlerAction = handlerAction)

        }

        else -> {

        }
    }
}


@UnstableApi
@Composable
fun VideoDetailScreen(player: Player, handlerAction: (VideoDetailAction) -> Unit) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)

    ) {
        val (videoPlayer) = createRefs()
        TopTopVideoPlayer(player = player, modifier = Modifier
            .clickable(onClick = {
                handlerAction(VideoDetailAction.ToggleVideo)
            })
            .constrainAs(videoPlayer) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
    }
}

@UnstableApi
@Composable
fun TopTopVideoPlayer(modifier: Modifier, player: Player) {
    var isFullScreen by remember {
        mutableStateOf(true)
    }

    AndroidView(factory = { context ->
        PlayerView(context).also {
            it.player = player
            it.useController = false
            it.useController = true
            it.setFullscreenButtonClickListener {
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
    }, modifier = modifier)
}

//@Composable
//fun Film(
//    movie: Movie,
//    navController: NavController,
//    movies:List<Movie>
//) {
//    val calendar = Calendar.getInstance()
//
//    val viewModel: PlayMovieViewModel = hiltViewModel()
//    var lifecycle by remember {
//        mutableStateOf(Lifecycle.Event.ON_CREATE)
//    }
//    val lifecycleOwner = LocalLifecycleOwner.current
//    DisposableEffect(lifecycleOwner) {
//        val observer = LifecycleEventObserver { _, event ->
//            lifecycle = event
//        }
//        lifecycleOwner.lifecycle.addObserver(observer)
//
//        onDispose {
//            lifecycleOwner.lifecycle.removeObserver(observer)
//        }
//    }
//    var isFullScreen by remember {
//        mutableStateOf(true)
//    }
//    Scaffold() {
//            paddingValues ->
//        Column(modifier = Modifier.fillMaxSize().background(Color.Black).padding(paddingValues)) {
//            AndroidView(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(Color.Black),
//                factory = { context ->
//                    PlayerView(context).also {
//                        //it.player = viewModel.player
//                        it.setFullscreenButtonClickListener { view ->
//                            with(context) {
//                                if (isFullScreen) {
//                                    setScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
//                                } else {
//                                    setScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
//                                }
//                                isFullScreen = !isFullScreen
//                            }
//                        }
//                    }
//                },
//                update = {
//                    when (lifecycle) {
//                        Lifecycle.Event.ON_PAUSE -> {
//                            it.onPause()
//                            it.player?.pause()
//                        }
//
//                        Lifecycle.Event.ON_RESUME -> {
//                            it.onResume()
//                        }
//
//
//                        else -> Unit
//                    }
//                },
//            )
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(StyleStatic.primaryModeColor)
//            ) {
//                LazyColumn(
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    item {
//                        Box(
//                            contentAlignment = Alignment.BottomCenter
//                        ) {
//
//                            repeat(3) {
//                                Box(
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//                                        .height(60.dp)
//                                        .background(
//                                            brush = Brush.verticalGradient(
//                                                colors = listOf(
//                                                    Color.Transparent,
//                                                    StyleStatic.primaryModeColor
//                                                )
//                                            )
//                                        )
//                                )
//                            }
//                        }
//                        Column(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(horizontal = 16.dp)
//                        ) {
//                            Text(
//                                text = movie!!.name.toString(),
//                                style = StyleStatic.textCommonStyle.copy(
//                                    fontSize = 38.sp,
//                                    fontFamily = fontFamilyHeading,
//                                    fontWeight = FontWeight.Bold
//                                )
//                            )
//
//                            Row(
//                                modifier = Modifier.padding(top = 12.dp, bottom = 4.dp)
//                            ) {
//                                calendar.time = movie.releaseDate?.toDate()
//                                val styleInRow = StyleStatic.textCommonStyle.copy(
//                                    fontSize = 14.sp,
//                                    fontWeight = FontWeight.SemiBold,
//                                    color = StyleStatic.blurTextWhiteColor
//                                )
//                                val infos = listOf(calendar.get(Calendar.YEAR).toString(),movie.episodeTotal.toString(), movie.time.toString())
//
//                                InfoSpaceDot(infos = infos,style = styleInRow)
//                            }
//
//                            Text(
//                                text = movie.description.toString(),
//                                style = StyleStatic.textCommonStyle.copy(
//                                    fontSize = 16.sp,
//                                    fontWeight = FontWeight.Normal,
//                                    textIndent = TextIndent(6.sp)
//                                ),
//                                maxLines = 4,
//                                modifier = Modifier.padding(vertical = 8.dp),
//                                overflow = TextOverflow.Ellipsis
//                            )
//
//                            InfoTopicFilm(
//                                topic = "Quốc gia",
//                                infomation = movie.country.toString()
//                            )
//
//                            InfoCategoryFilm(
//                                topic = "Thể loại",
//                                infomation = movie.category!!
//
//                            )
//
//                            ButtonPlay(
//                                onClick = {
//
//                                },
//                                modifier = Modifier
//                                    .padding(vertical = 12.dp)
//                                    .fillMaxWidth(),
//                                fSize = 18
//                            )
//
//                            Row(modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)) {
//                                IconDetail(
//                                    icon = Icons.Outlined.Add,
//                                    description = "Thêm vào DS",
//                                    colorText = StyleStatic.blurTextWhiteColor,
//                                    modifier = Modifier.padding(end = 16.dp))
//
//                                IconDetail(
//                                    icon = Icons.Outlined.Share,
//                                    description = "Chia sẻ",
//                                    colorText = StyleStatic.blurTextWhiteColor,
//                                    modifier = Modifier.padding(end = 16.dp))
//                                IconDetail(
//                                    icon = Icons.Default.FavoriteBorder,
//                                    description = "Yêu thích",
//                                    colorText = StyleStatic.blurTextWhiteColor,
//                                    modifier = Modifier.padding(end = 16.dp))
//                            }
//                        }
//                        CarouselListFilms(movie = movie!!,navController,movies,viewModel)
//                        Spacer(modifier = Modifier.height(30.dp))
//                    }
//                }
//
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 6.dp),
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    IconBackBlur(
//                        icon = Icons.Default.ArrowBack,
//                        colorIcon = StyleStatic.primaryTextColor,
//                        size = "small",
//                        onClick = {
//                            navController.popBackStack()
//                        }
//                    )
//
//                    IconBackBlur(
//                        icon = Icons.Default.Close,
//                        colorIcon = StyleStatic.primaryTextColor,
//                        size = "small",
//                        onClick = {
//
//                            navController.navigate(NavigationItem.Home.route)
//                        }
//                    )
//                }
//            }
//        }
//    }
//}


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