package com.example.moviesapp.model

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryMovie(var id: String = "", var name: String = "", var image: String = "") :
    Parcelable {

    companion object {

        fun from(value: String) = adapter().fromJson(value) ?: CategoryMovie()

        fun adapter(): JsonAdapter<CategoryMovie> =
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                .adapter<CategoryMovie>(CategoryMovie::class.java)

    }

    override fun toString(): String {
        return adapter().toJson(this)
    }


}


class CategoryMovieNavType : NavType<CategoryMovie>(isNullableAllowed = true) {
    override fun get(bundle: Bundle, key: String): CategoryMovie? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): CategoryMovie {
        return CategoryMovie.from(value)
    }

    override fun put(bundle: Bundle, key: String, value: CategoryMovie) {
        bundle.putParcelable(key, value)
    }
}

object CategoryMovieBookNavigation {
    const val categoryMovieArg = "categoryMovieArg"
    const val route = "categoryMovieBook?${categoryMovieArg}={$categoryMovieArg}"


    fun createRoute(categoryMovie: CategoryMovie): String {
        return "categoryMovieBook?${categoryMovieArg}=${categoryMovie.toString()}"
    }

    fun from(entry: NavBackStackEntry) =
        entry.arguments?.getParcelable<CategoryMovie>(categoryMovieArg)

}