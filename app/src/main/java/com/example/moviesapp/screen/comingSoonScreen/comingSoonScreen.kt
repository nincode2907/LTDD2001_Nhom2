package com.example.moviesapp.screen.comingSoonScreen

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import codes.andreirozov.bottombaranimation.ui.theme.fontFamilyHeading
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplication.screen.mainScreen.MainViewModel
import com.example.petadoption.bottomnav.BottomBar
import com.example.moviesapp.R
import com.example.moviesapp.model.Movie
import com.example.moviesapp.model.MovieBookNavigation
import com.example.moviesapp.screen.homeScreen.component.StyleStatic
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComingSoonScreen(
    mainViewModel: MainViewModel,
    navController: NavController,
    movies: List<Movie>,
) {

    
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(Color.Black),
                title = {
                    Text(
                        text = "Sắp ra mắt",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },

                )
        },
        bottomBar = {
            BottomBar(
                mainViewModel = mainViewModel,
                navController = navController,
                bottomBarState = true
            )
        },
    ) { paddingValues ->

        LazyColumn(
            Modifier
                .padding(paddingValues = paddingValues)
                .background(Color.Black)
        ) {
            items(movies.sortedByDescending { it.releaseDate }.take(3)) { movie ->
                MovieList(movie = movie) {
                    navController.navigate(MovieBookNavigation.createRoute(movie = movie))
                }
            }
        }
    }
}

@SuppressLint("NewApi")
@Composable
fun MovieList(
    movie: Movie, onClick: () -> Unit
) {
    val context = LocalContext.current
    var isSaved by remember{ mutableStateOf(false) }
    Column(
        modifier = Modifier
            .background(Color.Black)
            .padding(10.dp)
            .clickable(onClick = onClick),
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
                    text = "Ngày " + movie.releaseDate?.toDate()?.day,
                    color = Color.White,
                    fontSize = 17.sp, fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Tháng " + movie.releaseDate?.toDate()?.month,
                    color = Color(0xFFB3B3B3),
                    fontSize = 15.sp, fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Italic
                )
            }
        }
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(movie.image)
                .crossfade(true)
                .build(),
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
                    modifier = Modifier.weight(2f),
                    text = movie.name.toString(),
                    style = StyleStatic.textCommonStyle.copy(
                        fontSize = 30.sp,
                        fontFamily = fontFamilyHeading,
                        fontWeight = FontWeight.Bold
                    )

                )
                Spacer(modifier = Modifier.width(35.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    IconButtonView(
                        modifier = Modifier
                            .absolutePadding(left = 10.dp)
                            .fillMaxHeight()
                            .width(50.dp),
                        img = if (isSaved) R.drawable.ic_save else R.drawable.ic_save1,
                        title = if (isSaved) "Saved" else "Save"
                    ) {
                        val message = if (isSaved) "Đã hủy thêm vào DS" else "Đã thêm vào DS"
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        isSaved = !isSaved
                    }

                }
                IconButtonView(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .width(25.dp), img = R.drawable.ic_share, title = "Share"
                ) {
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

            }
        }
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




