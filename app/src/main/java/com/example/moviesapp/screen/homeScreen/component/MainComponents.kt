package com.example.moviesapp.screen.homeScreen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Info
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.screen.homeScreen.images
import com.example.moviesapp.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
@OptIn(ExperimentalPagerApi::class)
@Composable
fun Carousel() {
    val pagerState = rememberPagerState(initialPage = 2)
    var scope = rememberCoroutineScope()
    var liked by remember { mutableStateOf(false) }

    val configuration = LocalConfiguration.current

    // Chiều cao sẽ bằng 70% kích thước màn hình
    val screenHeight = configuration.screenHeightDp.dp
    val height = screenHeight * 0.8f

    // Chiều cao của ảnh
    val imageHeight = height - 80.dp


//    LaunchedEffect(Unit) {
//        while (true) {
//            delay(4000)
//            scope.launch {
//                pagerState.animateScrollToPage(pagerState.currentPage + 1)
//            }
//        }
//    }

    HorizontalPager(
        count = images.size,
        state = pagerState,
        modifier = Modifier.height(height),
        verticalAlignment = Alignment.Top
    ) { page ->
        var colorLikeIcon = if (liked) colorResource(id = R.color.tym) else styleStatic.primaryTextColor
        Column(

        ) {
            Box(
                contentAlignment = Alignment.BottomCenter
            ) {
                Image(
                    painter = painterResource(id = images[page]),
                    contentDescription = null,
                    modifier = Modifier.height(imageHeight),
                    contentScale = ContentScale.Crop
                )

                repeat(2) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        colorResource(id = R.color.black)
                                    )
                                )
                            )
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconDetail(
                    Icons.Outlined.Favorite,
                    "Thêm vào DS",
                    colorIcon = colorLikeIcon,
                    modifier = Modifier
                        .weight(2.5f)
                        .clickable {
                            liked = !liked

                        }
                )
                ButtonPlay(
                    onClick = {
                        liked = !liked
                    },
                    modifier = Modifier
                        .weight(5f)
                        .padding(horizontal = 8.dp)
                )
                IconDetail(
                    Icons.Outlined.Info,
                    "Chi tiết",
                    modifier = Modifier.weight(2f)
                )
            }
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(images.size) { it ->
            val color =
                if (pagerState.currentPage == it) Color.White else colorResource(id = R.color.gray)

            Box(
                modifier = Modifier
                    .padding(3.dp)
                    .clip(CircleShape)
                    .size(8.dp)
                    .background(color)
                    .clickable {
                        scope.launch {
                            pagerState.animateScrollToPage(it)
                        }
                    }
            )
        }
    }
}

@Composable
fun ListFilmHorizontal(
    films: List<Int> ,
    categoryFilm: String = "Phim Mới",
    modifier: Modifier = Modifier
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = categoryFilm,
            style = styleStatic.textCommonStyle.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "Xem tất cả",
            tint = styleStatic.primaryTextColor
        )
    }

    LazyRow(
        contentPadding = PaddingValues(horizontal = 18.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(films.size) { film ->

                FilmInList(painterReso = films[film], description = "")
        }
        item(){
            FilmSeeMore()

        }
    }
    Spacer(modifier = Modifier.height(18.dp))
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun ListFilmTop5(
    films: List<Int> ,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Phim Top 5",
        style = styleStatic.textCommonStyle.copy(
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        ),
        modifier = Modifier
            .padding(horizontal = 18.dp)
    )

    LazyRow(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        items(5) { film ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomStart,
            ) {
                Row(

                )
                {
                    Spacer(modifier = Modifier.width(35.dp))
                    FilmInList(painterReso = films[film], description = "")
                }

                Text(
                    text = (film + 1).toString(),
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
    }
    Spacer(modifier = Modifier.height(18.dp))
}