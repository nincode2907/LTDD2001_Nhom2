package com.example.moviesapp.data

import android.content.ContentValues
import android.util.Log
import com.example.moviesapp.model.CategoryMovie
import com.example.moviesapp.model.Movie
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class MovieRepository {
    val db = Firebase.firestore

    suspend fun getAllMovies(): List<Movie> = withContext(Dispatchers.IO) {
        var movies: MutableList<Movie> = mutableListOf<Movie>()
        try {
            val result = db.collection("movie").get().await()
            for (document in result) {
                val movie = Movie(
                    document.id,
                    document.data.get("name").toString(),
                    document.data.get("image").toString(),
                    document.data.get("categoryId").toString()
                )

                movies.add(movie)
            }

        } catch (exception: Exception) {
            Log.w(ContentValues.TAG, "Error getting documents.", exception)
        }
        movies
    }

    suspend fun getMoviesLimit(limit:Long): List<Movie> = withContext(Dispatchers.IO) {
        var movies: MutableList<Movie> = mutableListOf<Movie>()
        try {
            val result = db.collection("movie").limit(limit).get().await()
            for (document in result) {
                val movie = Movie(
                    document.id,
                    document.data.get("name").toString(),
                    document.data.get("image").toString(),
                    document.data.get("categoryId").toString()
                )

                movies.add(movie)
            }

        } catch (exception: Exception) {
            Log.w(ContentValues.TAG, "Error getting documents.", exception)
        }
        movies
    }


    suspend fun getAllMoviesByIdCategory(idCategory:String): List<Movie> = withContext(Dispatchers.IO) {
        var movies: MutableList<Movie> = mutableListOf<Movie>()
        try {
            val result = db.collection("movie").whereEqualTo("categoryId",idCategory).get().await()
            for (document in result) {
                val movie = Movie(
                    document.id,
                    document.data.get("name").toString(),
                    document.data.get("image").toString(),
                    document.data.get("categoryId").toString()
                )
                Log.d("XXX",movie.toString())
                movies.add(movie)
            }

        } catch (exception: Exception) {
            Log.w(ContentValues.TAG, "Error getting documents.", exception)
        }
        movies
    }

}