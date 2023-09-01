package com.example.moviesapp.screen.playMovieScreen


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import codes.andreirozov.bottombaranimation.ui.theme.fontFamilyHeading
import com.example.moviesapp.R
import com.example.moviesapp.model.Movie
import com.example.moviesapp.presentation.favourite.FavouriteMoviesModel
import com.example.moviesapp.screen.homeScreen.component.CarouselListFilms
import com.example.moviesapp.screen.homeScreen.component.IconDetail
import com.example.moviesapp.screen.homeScreen.component.InfoCategoryFilm
import com.example.moviesapp.screen.homeScreen.component.InfoSpaceDot
import com.example.moviesapp.screen.homeScreen.component.InfoTopicFilm
import com.example.moviesapp.screen.homeScreen.component.StyleStatic
import com.example.myapplication.screen.PlayMovieScreen.PlayMovieViewModel
import com.example.myapplication.screen.PlayMovieScreen.VideoDetailAction
import com.example.myapplication.screen.PlayMovieScreen.VideoDetailUiState
import kotlinx.coroutines.launch
import java.util.Calendar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@UnstableApi
@Composable
fun Film(
    movie: Movie,
    navController: NavController,
    movies: List<Movie>,
    movieFavourites: List<Movie>,
    viewModel: FavouriteMoviesModel
) {
    val videoViewModel: PlayMovieViewModel = hiltViewModel(key = movie.id)

    val calendar = Calendar.getInstance()
    val context = LocalContext.current
    var maxLineDes by remember { mutableStateOf(4) }
    var liked by remember { mutableStateOf(false) }
    var colorLikeIcon =
        if (liked) colorResource(id = R.color.tym) else StyleStatic.primaryTextColor

    var isFavourite by remember { mutableStateOf(movie?.id?.let { movieId ->
        movieFavourites.any { it.id == movieId }
    } ?: false) }
    var rotationState by remember { mutableStateOf(0f) }
    val rotation by animateFloatAsState(
        targetValue = if (isFavourite) 360f else 0f,
        animationSpec = tween(durationMillis = 500)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)

    ) {
        VideoDetailScreen(movie, videoViewModel)
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)) {
            Text(
                text = movie!!.name.toString(),
                style = StyleStatic.textCommonStyle.copy(
                    fontSize = 38.sp,
                    fontFamily = fontFamilyHeading,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
            Row(
                modifier = Modifier.padding(top = 12.dp, bottom = 4.dp)
            ) {
                calendar.time = movie.releaseDate?.toDate()
                val styleInRow = StyleStatic.textCommonStyle.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = StyleStatic.blurTextWhiteColor
                )
                val infos = listOf(
                    calendar.get(Calendar.YEAR).toString(),
                    movie.episodeTotal.toString(),
                    movie.time.toString()
                )

                InfoSpaceDot(infos = infos, style = styleInRow)
            }
            Text(
                text = movie.description.toString(),
                style = StyleStatic.textCommonStyle.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    textIndent = TextIndent(6.sp)
                ),
                maxLines = maxLineDes,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        maxLineDes = if (maxLineDes == 4) 99 else 4
                    }
                    .animateContentSize(),
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
            Row(modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)) {
                IconDetail(
                    icon = if(isFavourite) Icons.Outlined.Check else Icons.Default.Add,
                    description = "Thêm vào DS",
                    colorText = StyleStatic.blurTextWhiteColor,
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .rotate(rotationState + rotation),
                    onClick = {
                        viewModel.viewModelScope.launch {
                            viewModel.updateFavourite(movie)
                        }
                        isFavourite = !isFavourite
                        rotationState += 360f
                    }
                )
                IconDetail(
                    icon = Icons.Outlined.Share,
                    description = "Chia sẻ",
                    colorText = StyleStatic.blurTextWhiteColor,
                    modifier = Modifier.padding(end = 16.dp),
                    onClick = {
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
                )
                IconDetail(
                    icon = if (liked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    description = "Yêu thích",
                    colorText = StyleStatic.blurTextWhiteColor,
                    colorIcon = colorLikeIcon,
                    modifier = Modifier.padding(end = 16.dp),
                    onClick = {
                        liked = !liked
                    }
                )
            }
        }
        CarouselListFilms(movie = movie!!,navController,movies, viewModel = videoViewModel)
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
fun VideoDetailScreen(
    uiState: VideoDetailUiState, player: Player, handlerAction: (VideoDetailAction) -> Unit
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
            .fillMaxWidth()
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