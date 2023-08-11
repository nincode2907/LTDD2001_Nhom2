package com.example.moviesapp.screen.homeScreen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviesapp.R
import com.example.moviesapp.model.Movie
import com.example.moviesapp.screen.homeScreen.component.StyleStatic.blurTextWhiteColor
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import java.util.Calendar

@Composable
fun IconDetail(
    icon: ImageVector,
    description: String,
    colorIcon: Color = StyleStatic.primaryTextColor,
    colorText: Color = StyleStatic.primaryTextColor,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Icon(
            imageVector = icon,
            contentDescription = description,
            modifier = Modifier
                .size(30.dp)
                .clip(RoundedCornerShape(percent = 50)),
            tint = colorIcon,
        )
        Text(
            text = description,
            color = colorText,
            style = TextStyle(fontSize = 10.sp)
        )
    }
}

@Composable
fun IconBackBlur(
    onClick: () -> Unit,
    icon: ImageVector,
    size: String,
    colorIcon: Color = StyleStatic.primaryTextColor,
    modifier: Modifier = Modifier
) {


    val sizeI = when(size) {
        "big" -> 60
        "small" -> 32
        else -> 46
    }

    Box(
        modifier = modifier
            .size(sizeI.dp)
            .background(
                Color(android.graphics.Color.parseColor("#33000000")),
                RoundedCornerShape(percent = 50)
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier
                .padding(all = 6.dp)
                .size((sizeI - 12).dp)
                .clip(RoundedCornerShape(percent = 50))
                .clickable { onClick() },
            tint = colorIcon
        )
    }
}

@Composable
fun ButtonPlay(
    onClick: () -> Unit,
    icon: ImageVector = Icons.Default.PlayArrow,
    text: String = "Xem phim",
    fSize: Int,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(Color.Blue),
        shape = RoundedCornerShape(percent = 50),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = StyleStatic.primaryTextColor
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = text,
                style = StyleStatic.textCommonStyle.copy(
                    fontSize = fSize.sp,
                    fontWeight = FontWeight.Normal
                )
            )
        }
    }
}


@Composable
fun FilmSeeMore() {
    Box(
        modifier = StyleStatic.modifierFilmInListSize
            .background(colorResource(id = R.color.dark), RoundedCornerShape(6.dp)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.seemore),
            contentDescription = "Xem tất cả",
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
                .graphicsLayer(alpha = 0.2f),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "XEM TẤT CẢ",
            style = StyleStatic.textCommonStyle
        )
    }
}

@Composable
fun InfoTopicFilm(
    topic: String,
    infomation: String,
    style: TextStyle = StyleStatic.textCommonStyle.copy(fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        color = StyleStatic.blurTextWhiteColor
    )
) {
    Row(
        modifier = Modifier.padding(top = 4.dp)
    ) {
        Text(
            text = "$topic:",
            style = style,
            modifier = Modifier.width(70.dp)
        )
        Text(
            text = infomation,
            style = style
        )
    }
}
@Composable
fun FilmInList(
    imageUrl: String,
    modifier: Modifier = StyleStatic.modifierFilmInListSize
        .clip(RoundedCornerShape(6.dp)),
    onClick: () -> Unit
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = null,
        modifier = modifier
            .clickable { onClick() },
        contentScale = ContentScale.Crop
    )
}



@Composable
fun InfoCategoryFilm(
    topic: String,
    infomation: List<String>,
    color: Color = StyleStatic.blurTextWhiteColor
) {
    LazyRow(
        modifier = Modifier.padding(top = 4.dp)
    ) {
        item() {
            Text(
                text = "$topic:",
                style = StyleStatic.textCommonStyle.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = color
                ),
                modifier = Modifier.width(70.dp)
            )
        }
        items(infomation) { it ->
            Text(
                text = it + ", ",
                style = StyleStatic.textCommonStyle.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = color
                )
            )
        }
    }
}

@Composable
fun ItemRelatedFilm(
    movie: Movie,
    onClick: () -> Unit
) {
    val calendar = Calendar.getInstance()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(92.dp)
            .padding(bottom = 8.dp)
            .clickable { onClick() }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(end = 10.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.image)
                    .crossfade(true)
                    .build(),
                contentDescription = movie.name,
                modifier = Modifier
                    .width(180.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            IconBackBlur(
                icon = Icons.Default.PlayArrow,
                colorIcon = StyleStatic.primaryTextColor,
                size = "small",
                onClick = {
                    onClick()
                }
            )
        }

        Column {
            Text(
                text = movie.name.toString(),
                style = StyleStatic.textCommonStyle.copy(
                    fontSize = 14.sp
                )
            )

            Row(
                modifier = Modifier.padding(top = 1.dp, bottom = 1.dp)
            ) {
                calendar.time = movie.releaseDate
                val styleInRow = StyleStatic.textCommonStyle.copy(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = StyleStatic.blurTextWhiteColor
                )
                val infos = listOf(calendar.get(Calendar.YEAR).toString(),movie.time.toString())
                InfoSpaceDot(infos = infos,style = styleInRow)
            }

            Text(
                text = movie.description.toString(),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = StyleStatic.textCommonStyle.copy(
                    fontSize = 12.sp
                )
            )
        }
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun ItemMovieTop5(
    imageUrl: String,
    number: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomStart,
    ) {
        Row()
        {
            Spacer(modifier = Modifier.width(35.dp))
            FilmInList(imageUrl = imageUrl, onClick = {
                onClick()
            })
        }
        Text(
            text = number.toString(),
            style = TextStyle(
                fontSize = 130.sp,
                fontWeight = FontWeight.ExtraBold,
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        colorResource(id = R.color.black),
                        Color.White
                    )
                ),
                lineHeight = 0.sp,
                textAlign = TextAlign.Start,
                letterSpacing = 0.sp
            ),
            modifier = Modifier
                .padding(all = 0.dp)
                .height(135.dp)
        )
    }
}

@Composable
fun ItemPoster(
    imageUrl: String,
    heightImg: Dp,
    navController: NavController,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.BottomCenter
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clickable { onClick() },
            contentScale = ContentScale.Crop
        )
        repeat(3) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
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
        Row(
            modifier = Modifier.padding(start = 10.dp, end = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconDetail(
                Icons.Outlined.FavoriteBorder,
                "Thêm vào DS",
                modifier = Modifier
                    .clickable {

                    }
            )
            ButtonPlay(
                onClick = {
                    onClick()
                }, fSize = 14,
                modifier = Modifier
                    .weight(5f)
                    .padding(horizontal = 8.dp)
            )
            IconDetail(
                Icons.Outlined.Info,
                "Chi tiết",
                modifier = Modifier
                    .clickable {
                        onClick()
                    }
            )
        }
    }
}

@Composable
fun InfoSpaceDot(
    infos: List<String>,
    style: TextStyle
) {
   for(info in infos) {
       if(info != infos.get(0))
           Text(text = "•",
               style = style,
               modifier = Modifier.padding(horizontal = 4.dp)
           )
       Text(text = info, style = style)
   }
}

//@Composable
//fun YoutubeTrailer(trailerUrl: String) {
//    AndroidView(
//        modifier = Modifier.fillMaxWidth()
//            .height(360.dp),
//        factory = {context ->
//            WebView(context).apply {
//                settings.javaScriptEnabled = true
//                webViewClient = WebViewClient()
//                loadUrl(trailerUrl)
//            }
//    })
//}

@Composable
fun YoutubeTrailer(
    videoId: String,
) {
    val ctx = LocalContext.current
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = {
            var view = YouTubePlayerView(it)
            val fragment = view.addYouTubePlayerListener(
                object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        super.onReady(youTubePlayer)
                        youTubePlayer.loadVideo(videoId, 0f)
                        youTubePlayer.pause()
                        youTubePlayer.mute()
                    }
                }
            )
            view
        })
}
