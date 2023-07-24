package com.example.moviesapp.screen.homeScreen.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviesapp.R

object styleStatic {
    var primaryTextColor = Color.White
    var primaryModeColor = colorResource(id = R.color.dark)

    var textCommonStyle = TextStyle(
        fontSize = 13.sp,
        color = primaryTextColor
    )

    var modifierFilmInListSize = Modifier
        .width(120.dp)
        .height(185.dp)
}