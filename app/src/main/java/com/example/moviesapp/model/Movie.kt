package com.example.moviesapp.model

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Movie(
    var id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val image: String? = null,
    val releaseDate: Date? = null,
    val episodeTotal: String? = null,
    val time: String? = null,
    val country: String? = null,
    val trailer: String? = null,
    val category: List<String>? = null,
    val view: Int? = null,
    val outstanding: Boolean? = false
) : Parcelable {
    companion object {

        fun from(value: String) = adapter().fromJson(value) ?: Movie()

        fun adapter(): JsonAdapter<Movie> = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            .adapter<Movie>(Movie::class.java)
    }

    override fun toString(): String {
        return adapter().toJson(this)
    }


}


class MoviesNavType : NavType<Movie>(isNullableAllowed = true) {
    override fun get(bundle: Bundle, key: String): Movie? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Movie {
        return Movie.from(value)
    }

    override fun put(bundle: Bundle, key: String, value: Movie) {
        bundle.putParcelable(key, value)
    }
}

object MovieNavigation {
    const val movieArg = "movieArg"
    const val route = "movie?${movieArg}={$movieArg}"


    fun createRoute(movie: Movie): String {
        return "movie?${movieArg}=${movie.toString()}"
    }

    fun from(entry: NavBackStackEntry) = entry.arguments?.getParcelable<Movie>(movieArg)

}