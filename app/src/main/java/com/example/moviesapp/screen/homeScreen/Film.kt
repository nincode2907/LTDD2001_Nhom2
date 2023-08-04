package com.example.moviesapp.screen.homeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviesapp.R
import com.example.moviesapp.screen.homeScreen.component.ButtonPlay
import com.example.moviesapp.screen.homeScreen.component.CarouselListFilms
import com.example.moviesapp.screen.homeScreen.component.IconBackBlur
import com.example.moviesapp.screen.homeScreen.component.IconDetail
import com.example.moviesapp.screen.homeScreen.component.InfoTopicFilm
import com.example.moviesapp.screen.homeScreen.component.StyleStatic
import com.example.moviesapp.screen.homeScreen.component.filmInfo

class Film  {
}

data class FilmInfo(
    val name : String,
    val description : String,
    val thumbnail : String,
    val poster : String,
    val yearRelease : Int,
    val episodeTotal : String,
    val time : String,
    val country : String,
    val category: String,
)

@Composable
fun Page(){
    var isExten = false;

    Box(
        modifier = Modifier.fillMaxWidth()
            .background(StyleStatic.primaryModeColor)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                contentAlignment = Alignment.BottomCenter
            ) {
                Image(
                    painterResource(id = R.drawable.suzumeposter),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .padding(bottom = 4.dp),
                    contentScale = ContentScale.Crop
                )

                repeat(2) {
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
//            AsyncImage(
//                model = ImageRequest.Builder(LocalContext.current)
//                    .data(filmInfo.poster)
//                    .crossfade(true)
//                    .build(),
//                contentDescription = filmInfo.name,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(240.dp)
//                    .padding(bottom = 16.dp),
//                contentScale = ContentScale.Crop
//            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = filmInfo.name,
                    style = StyleStatic.textCommonStyle.copy(
                        fontSize = 34.sp,
                        fontFamily = FontFamily.Cursive,
                        fontWeight = FontWeight.SemiBold
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
                        text = filmInfo.yearRelease.toString(),
                        style = styleInRow
                    )

                    Text(
                        text = "•",
                        style = styleInRow,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )

                    Text(
                        text = filmInfo.episodeTotal,
                        style = styleInRow
                    )

                    Text(
                        text = "•",
                        style = styleInRow,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )

                    Text(
                        text = filmInfo.time,
                        style = styleInRow
                    )
                }

                Text(
                    text = filmInfo.description,
                    style = StyleStatic.textCommonStyle.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        textIndent = TextIndent(6.sp)
                    ),
                    maxLines = 4,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                InfoTopicFilm(
                    topic = "Quốc gia",
                    infomation = filmInfo.country
                )

                InfoTopicFilm(
                    topic = "Thể loại",
                    infomation = filmInfo.category
                )

                ButtonPlay(
                    onClick = {},
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .fillMaxWidth(),
                    fSize = 18
                )

                Row(modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)) {
                    IconDetail(
                        icon = Icons.Outlined.Add,
                        description = "Thêm vào DS",
                        modifier = Modifier.padding(horizontal = 5.dp))

                    IconDetail(
                        icon = Icons.Outlined.Share,
                        description = "Chia sẻ",
                        modifier = Modifier.padding(horizontal = 5.dp))

                    IconDetail(
                        icon = Icons.Default.FavoriteBorder,
                        description = "Yêu thích",
                        modifier = Modifier.padding(horizontal = 5.dp))
                }
            }
            CarouselListFilms()
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
            )

            IconBackBlur(
                icon = Icons.Default.Close,
                colorIcon = StyleStatic.primaryTextColor
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 564, heightDp = 1254)
@Composable
fun PreviewHome() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(id = R.color.dark)
    ) {
        Page()
    }
}