package com.example.movieapp.screen.homeScreen

import android.graphics.fonts.FontStyle
import android.widget.ScrollView
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontVariation.weight
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviesapp.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.log


@Composable
fun HomeScreen() {
   Column(
       Modifier
           .fillMaxSize()
           .background(Color.Black)) {
       App()
   }
}

val images = listOf(
    R.drawable.onepiece,
    R.drawable.naruto,
    R.drawable.demonslayer,
    R.drawable.jujutsukaisen,
    R.drawable.conan,
    R.drawable.blackclover
)

@Composable
fun App(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .background(colorResource(id = R.color.dark))
) {
    val scrollState = rememberScrollState()

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .verticalScroll(state = scrollState)
    ) {
        Carousel()
        Spacer(modifier = Modifier.height(20.dp))
        ListFilmHorizontal(films = images, categoryFilm = "Phim Thể Loại Top 1 Khu Vực")
        ListFilmTop5(films = images.reversed())
        ListFilmHorizontal(films = images)
        ListFilmHorizontal(films = images.reversed(), categoryFilm = "Trinh Thám")
        ListFilmHorizontal(films = images.reversed(), categoryFilm = "Anime")
        Spacer(modifier = Modifier.height(50.dp))
    }
}

@Composable
fun IconDetail(
    icon: ImageVector,
    description: String,
    colorIcon: Color = Color.White,
    colorText: Color = Color.White,
    modifier: Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(16.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = description,
            modifier = modifier.size(30.dp),
            tint = colorIcon,
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = description,
            color = colorText,
            style = TextStyle(fontSize = 10.sp)
        )
    }
}

@Composable
fun ButtonPlay(
    onClick: () -> Unit,
    icon: ImageVector = Icons.Default.PlayArrow,
    text: String = "Xem phim",
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
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = text,
                style = LocalTextStyle.current.copy(
                    fontSize = 16.sp,
                    color = Color.White
                )
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)

@Composable
fun Carousel() {
    val pagerState = rememberPagerState(initialPage = 2)
    var scope = rememberCoroutineScope()
    var liked by remember { mutableStateOf(false) }
    // Lấy thông tin cấu hình thiết bị
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
        var colorLikeIcon = if (liked) colorResource(id = R.color.tym) else Color.White
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
                        .height(48.dp)
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
            color = Color.White,
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "Xem tất cả",
            tint = Color.White
        )
    }

    LazyRow(
        contentPadding = PaddingValues(horizontal = 18.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(films.size) { film ->
            if (film == images.size - 1)
                FilmSeeMore()
            else
                Image(
                    painter = painterResource(id = films[film]),
                    contentDescription = null,
                    modifier = Modifier
                        .width(120.dp)
                        .height(185.dp)
                        .padding(end = 2.dp)
                        .clip(RoundedCornerShape(6.dp)),
                    contentScale = ContentScale.Crop
                )
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
        color = Color.White,
        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
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
                        Image(
                            painter = painterResource(id = films[film]),
                            contentDescription = null,
                            modifier = Modifier
                                .width(120.dp)
                                .height(185.dp)
                                .padding(end = 2.dp)
                                .clip(RoundedCornerShape(6.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Text(
                        text = (film + 1).toString(),
                        color = Color.White,
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

@Composable
fun FilmSeeMore() {

    Box(
        modifier = Modifier
            .width(120.dp)
            .height(185.dp)
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
            color = Color.White,
            style = TextStyle(fontSize = 13.sp, fontWeight = FontWeight.Bold)
        )
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

//@Preview(showBackground = true, widthDp = 574, heightDp = 936)
@Preview(showBackground = true, widthDp = 564, heightDp = 1254)
@Composable
fun PreviewHome() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(id = R.color.dark)
    ) {
        App()
    }
}
//@Composable
//fun HomeScreen(
//    mainViewModel: MainViewModel,
//    navController: NavController,
//    bottomBarState: MutableState<Boolean>
//) {
//    Scaffold(
//        bottomBar = {
//            BottomBar(
//                mainViewModel = mainViewModel,
//                navController = navController,
//                bottomBarState = bottomBarState
//            )
//        },
//    ) { paddingValues ->
//
//        Column(
//            Modifier
//                .fillMaxSize()
//                .padding(paddingValues = paddingValues)
//                .background(Color.Black)
//        ) {
//            Text(text = "HomeScreen", color = Color.White)
//        }
//    }