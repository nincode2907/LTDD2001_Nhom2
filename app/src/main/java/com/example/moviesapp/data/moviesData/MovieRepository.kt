package com.example.moviesapp.data.moviesData

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.moviesapp.model.CategoryMovie
import com.example.moviesapp.model.Movie
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepository @Inject constructor(var moviesRemoteDataSource: MoviesRemoteDataSource) {
    suspend fun getAllMovies(): List<Movie> {
       return moviesRemoteDataSource.getAllMovies()
    }

    suspend fun getMoviesPoster(): List<Movie> {
        return moviesRemoteDataSource.getMoviesPoster()
    }


}