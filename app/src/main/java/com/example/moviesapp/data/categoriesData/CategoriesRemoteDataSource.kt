package com.example.moviesapp.data.categoriesData

import com.example.moviesapp.model.CategoryMovie
import com.example.moviesapp.model.Movie
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CategoriesRemoteDataSource @Inject constructor(var db: FirebaseFirestore) {

    suspend fun getAllCategory(): List<CategoryMovie> {
        var categoryMovie: MutableList<CategoryMovie> = mutableListOf<CategoryMovie>()
        val result = db.collection("category").get().await()
        for (document in result) {
            val categoryMovieDocument = document.toObject<CategoryMovie>()

            categoryMovie.add(categoryMovieDocument)
        }
        return categoryMovie
    }
}