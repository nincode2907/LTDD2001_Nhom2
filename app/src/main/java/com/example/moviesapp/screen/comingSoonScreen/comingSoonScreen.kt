package com.example.moviesapp.screen.comingSoonScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.screen.mainScreen.MainViewModel
import com.example.petadoption.bottomnav.BottomBar
import com.example.moviesapp.R
import com.example.moviesapp.screen.categoryMoviesCreen.TopBarScreen
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComingSoonScreen(
    mainViewModel: MainViewModel,
    navController: NavController,
    bottomBarState: MutableState<Boolean>
) {
    var isShowBottomSheet by remember {
        mutableStateOf(false)
    }

    val coroutine = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(Color.Black),
                title = {
                    TopBarScreen(title = "Sắp ra mắt")
                },

                )
        },
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
                .padding(paddingValues = paddingValues)
                .background(Color.Black)
        ) {
            items(movieList) { movie ->
                MovieList(
                    movie = movie
                )

            }

            item {
                Button(onClick = { isShowBottomSheet = true }) {

                }
                if (isShowBottomSheet) {
                    ModalBottomSheet(onDismissRequest = {
                        coroutine.launch {
                            sheetState.hide()
                        }.invokeOnCompletion {
                            isShowBottomSheet = false
                        }
                    }, sheetState = sheetState) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Red)
                        )
                    }
                }
            }

        }
    }
}


data class Movies(
    val day: Int,
    val month: Int,
    val title: String,
    val imageRes: Int,
    val content: String
)

val movieList = listOf(
    Movies(22, 6, "One Piêc", R.drawable.tntm, " Nguyệt Tẫn Minh"),
    Movies(11, 12, "TNTM ", R.drawable.tntm, "Trường Nguyệt Tẫn Minh"),
    Movies(5, 9, "TNTM ", R.drawable.tntm, "Trường Nguyệt Tẫn Minh"),
    /*Thêm phim ở đây*/
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieList(
    movie: Movies,
) {
    var isShowBottomSheet by remember {
        mutableStateOf(false)
    }

    val coroutine = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    Column(
        modifier = Modifier
            .background(Color.Black)
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            Divider(
                modifier = Modifier
                    .height(height = 45.dp)
                    .width(width = 3.dp),
                color = Color(0xFF157AFF)
            )
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = "Ngày " + movie.day,
                    color = Color.White,
                    fontSize = 17.sp, fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Tháng " + movie.month,
                    color = Color(0xFFB3B3B3),
                    fontSize = 15.sp, fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Italic
                )
            }
        }
        Image(
            painter = painterResource(id = movie.imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(10.dp))
                .shadow(elevation = 25.dp),
            contentScale = ContentScale.Crop
        )
        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp),
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = movie.title,
                    fontSize = 28.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(2f)
                        .padding(end = 10.dp)
                )

                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    IconButtonView(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .width(90.dp), img = R.drawable.ic_save1, title = "Save"
                    ) {

                    }

                    IconButtonView(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .width(90.dp), img = R.drawable.ic_share, title = "Share"
                    ) {
                        isShowBottomSheet = true
                    }

                }
            }
        }

        Text(
            text = movie.content,
            fontSize = 17.sp,
            color = Color.White,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Start,
        )


    }
    if (isShowBottomSheet) {
        ModalBottomSheet(onDismissRequest = {
            coroutine.launch {
                sheetState.hide()
            }.invokeOnCompletion {
                isShowBottomSheet = false
            }
        }, sheetState = sheetState) {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.White))
        }
    }

}


@Composable
fun IconButtonView(modifier: Modifier, img: Int, title: String, onClick: () -> Unit) {
    IconButton(modifier = modifier, onClick = onClick)
    {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(id = img),
                contentDescription = "",
                modifier = Modifier
                    .size(30.dp), tint = Color.White
            )
            Text(
                text = title,
                fontSize = 12.sp,
                textAlign = TextAlign.Start,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {

}
