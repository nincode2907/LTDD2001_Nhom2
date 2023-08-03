package com.example.moviesapp.screen.homeScreen.component

import android.media.Image
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
import com.example.moviesapp.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Carousel() {
    val pagerState = rememberPagerState(initialPage = 0)
    var scope = rememberCoroutineScope()
    var liked by remember { mutableStateOf(false) }
    val configuration = LocalConfiguration.current

    // Chiều cao sẽ bằng 70% kích thước màn hình
    val screenHeight = configuration.screenHeightDp.dp
    val height = screenHeight * 0.8f

    // Chiều cao của ảnh
    val imageHeight = height - 80.dp

    var colorLikeIcon =
        if (liked) colorResource(id = R.color.tym) else styleStatic.primaryTextColor
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
            count = 5,
            state = pagerState,
            modifier = Modifier.height(height),
            verticalAlignment = Alignment.Top
        ) { page ->
            when (page) {
                0 -> ItemPoster(img = R.drawable.demonslayer)
                1 -> ItemPoster(img = R.drawable.demonslayer)
                2 -> ItemPoster(img = R.drawable.demonslayer)
                3 -> ItemPoster(img = R.drawable.demonslayer)
                4 -> ItemPoster(img = R.drawable.demonslayer)
                5 -> ItemPoster(img = R.drawable.demonslayer)
            }
        }
        Row(
            Modifier
                .height(50.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(5) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color.White else colorResource(id = R.color.gray)
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


@Composable
fun ItemPoster(img: Int) {

    Box(
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(id = img),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            colorResource(id = R.color.black)
                        )
                    )
                )
        )
        Row(
            modifier = Modifier.padding(start = 10.dp, end = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconDetail(
                Icons.Outlined.Favorite,
                "Thêm vào DS",
                colorIcon = Color.White,
                modifier = Modifier
                    .clickable {

                    }
            )
            ButtonPlay(
                onClick = {

                },
                modifier = Modifier
                    .weight(5f)
                    .padding(horizontal = 8.dp)
            )
            IconDetail(
                Icons.Outlined.Info,
                "Chi tiết",
                modifier = Modifier
                    .clickable {

                    }
            )
        }
    }
}


@Composable
fun ListFilmHorizontal(
    categoryFilm: String = "Phim Mới",
) {
    Column(modifier = Modifier.padding(15.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
        TitleRowViewMovie(categoryFilm)
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(5) { film ->
                FilmInList(description = "")
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
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun ListFilmTop5() {
    Column(modifier = Modifier.padding(15.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
        TitleRowViewMovie("Phim top 5")

        LazyRow() {
            items(5) { film ->
                ItemMovieTop5(R.drawable.demonslayer, film)
            }
        }
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun ItemMovieTop5(img: Int, number: Int) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomStart,
    ) {
        Row()
        {
            Spacer(modifier = Modifier.width(35.dp))
            FilmInList(description = "")
        }
        Text(
            text = (number + 1).toString(),
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

