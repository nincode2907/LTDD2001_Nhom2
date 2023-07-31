package com.example.rank.data

import android.media.Image

class Ranking (
    val id: String,
    val title: String,
    val category: String,
    val age: Int,
    val description: String,
    val puppyImageId: Int = 0
)

class Banner (
    val id: String,
    val bannerImage: Int = 0
)