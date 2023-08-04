package com.example.rank

import android.content.ClipData.Item
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.rank.data.Banner
import com.example.rank.data.DataProvider
import com.example.rank.data.DataProvider.banner
import codes.andreirozov.bottombaranimation.ui.theme.BottomBarAnimationTheme


import com.example.rank.data.DataProvider.bannerList
import com.example.rank.data.Ranking



@Composable
fun Banner() {
    val banners = remember {
        DataProvider.bannerList
    }

    LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {

        items(banners) {
            Card(
                modifier = Modifier
                    .padding(horizontal = 2.dp, vertical = 15.dp)
                    .height(100.dp)
                    .width(380.dp),
                elevation = CardDefaults.cardElevation(2.dp),
                shape = RoundedCornerShape(corner = CornerSize(5.dp))
            )
            {
                bannerImage(banner = it)
            }
        }
    }
}

@Composable
fun bannerImage (banner: Banner) {
    Image(painter = painterResource(id = banner.bannerImage), contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth(1f)
            .height(100.dp))
}

