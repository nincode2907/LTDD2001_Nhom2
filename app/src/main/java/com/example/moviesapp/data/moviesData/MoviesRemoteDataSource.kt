package com.example.moviesapp.data.moviesData

import android.content.ContentValues
import android.util.Log
import com.example.moviesapp.model.Movie
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor( var db: FirebaseFirestore) {
    suspend fun getAllMovies(): List<Movie> {
        var movies: MutableList<Movie> = mutableListOf<Movie>()
        val result = db.collection("movies").get().await()
        for (document in result) {
            val movie = document.toObject<Movie>()
            val date = document.getDate("releaseDate")

            movies.add(movie)
        }
        return movies
    }

    suspend fun getMoviesPoster(): List<Movie> {
        var movies: MutableList<Movie> = mutableListOf<Movie>()
        val result = db.collection("movies").whereEqualTo("outstanding",true).get().await()
        for (document in result) {
            val movie = document.toObject<Movie>()
            movies.add(movie)
        }
        return movies
    }

    suspend fun addMovie(movie: Movie) {
        val collection = db.collection("movies")
        collection.add(movie).await()
    }
}