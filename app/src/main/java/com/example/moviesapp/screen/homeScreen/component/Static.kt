package com.example.moviesapp.screen.homeScreen.component

import android.graphics.Color.parseColor
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object StyleStatic {
    val darkColor = "#1C1E1D";
    val whiteColor = "#F8F8F8";
    val whiteBlurColor = "#8C8C8C";
    val primaryTextColor : Color = Color(parseColor(whiteColor))
    var primaryModeColor: Color = Color(parseColor(darkColor));
    var blurTextWhiteColor: Color = Color(parseColor(whiteBlurColor));

    var textCommonStyle = TextStyle(
        fontSize = 13.sp,
        color = primaryTextColor,
        fontFamily = FontFamily.SansSerif
    )

    var modifierFilmInListSize = Modifier
        .width(120.dp)
        .height(185.dp)
}

object filmInfo {
    val name = "Suzume no Tojimari"
    val description = "Câu chuyện Suzume no Tojimari kể về Suzume, một cô gái 17 tuổi đến từ một thị trấn Kyushu yên tĩnh gặp một chàng trai trẻ đang tìm kiếm một cánh cửa. Họ tìm thấy một cánh cửa trong đống đổ nát trên núi, và Suzume đã mở nó. Chẳng bao lâu, nhiều cánh cửa hơn bắt đầu mở ra trên khắp Nhật Bản, kéo theo những thảm họa từ phía bên kia. Bộ phim mô tả sự giải phóng và trưởng thành của Suzume, khi cô đóng những cánh cửa đang gây ra thảm họa."
    val thurbnail = "https://wallpapercave.com/wp/wp11027141.jpg"
    val poster = "https://wallpaperaccess.com/full/8887832.jpg"
    val yearRelease = 2022
    val episodeTotal = "Trọn Bộ"
    val time = "121 phút"
    val country = "Nhật Bản"
    val category = "Phiêu Lưu, Anime, Movie & OVA, Giả Tưởng"
}

