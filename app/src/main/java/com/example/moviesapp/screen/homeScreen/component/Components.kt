package com.example.moviesapp.screen.homeScreen.component

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviesapp.R

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
            modifier = modifier.size(30.dp),
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
    icon: ImageVector,
    colorIcon: Color = StyleStatic.primaryTextColor,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(10.dp)
            .width(38.dp)
            .height(38.dp)
            .background(
                Color(android.graphics.Color.parseColor("#33000000")),
                RoundedCornerShape(percent = 50)
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = modifier.size(26.dp),
            tint = colorIcon,
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

        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = StyleStatic.primaryTextColor
        )
        Text(
            text = text,
            style = StyleStatic.textCommonStyle.copy(
                fontSize = 16.sp
            )
        )
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
fun FilmInList(
    painterReso: Int,
    modifier: Modifier = StyleStatic.modifierFilmInListSize
        .clip(RoundedCornerShape(6.dp))
) {
    Image(
        modifier = Modifier
            .width(120.dp)
            .height(185.dp)
            .clip(
                RoundedCornerShape(6.dp)
            )
            .padding(end = 2.dp),
        painter = painterResource(id = R.drawable.demonslayer),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}

@Composable
fun InfoTopicFilm(
    topic: String,
    infomation: String,
    color: Color = StyleStatic.blurTextWhiteColor
) {
    Row(
        modifier = Modifier.padding(top = 4.dp)
    ) {
        Text(
            text = "$topic:",
            style = StyleStatic.textCommonStyle.copy(
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = color
            ),
            modifier = Modifier.width(70.dp)
        )
        Text(
            text = infomation,
            style = StyleStatic.textCommonStyle.copy(
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = color
            )
        )
    }
}