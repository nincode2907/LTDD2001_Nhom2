package com.example.moviesapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryMovie(var id: String, var name: String,var image:String) : Parcelable {

}