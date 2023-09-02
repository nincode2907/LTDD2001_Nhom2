package com.example.myapplication.model

import androidx.annotation.DrawableRes
import com.example.moviesapp.R

sealed class NavigationItem(val route: String, val title: String,
                            @DrawableRes val image: Int, @DrawableRes val imageSelected: Int) {
    object Home : NavigationItem("Home", "Trang chủ", R.drawable.iconhome, R.drawable.iconhome)

    object Ranking : NavigationItem("Ranking", "Xếp hạng", R.drawable.iconrank   , R.drawable.iconrank)

    object Search : NavigationItem("Search", "Tìm kiếm", R.drawable.iconsearch, R.drawable.iconsearch)

    object ComingSoon : NavigationItem("comingSoon", "Sắp ra mắt", R.drawable.iconcalendar, R.drawable.iconcalendar)

    object PlayVideo : NavigationItem("PlayVideo", "PlayVideo", R.drawable.baseline_home_24, R.drawable.baseline_home_24)

    object User : NavigationItem("User", "User", R.drawable.iconuser, R.drawable.iconuser)

    object AnimatedSplash:NavigationItem("AnimatedSplash","AnimatedSplashScreen",R.drawable.baseline_home_24,R.drawable.baseline_home_24)
}
